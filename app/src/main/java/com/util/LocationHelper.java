package com.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.studioidan.popapp.interfaces.SuccessCallback;

public class LocationHelper {
    public static final String TAG = LocationHelper.class.toString();

    private static LocationHelper _instance = null;
    private Location lastLocation;

    //location tracker
    private FusedLocationProviderClient mFusedLocationClient;

    public static LocationHelper getInstance(Context context) {
        if (_instance == null)
            _instance = new LocationHelper(context);
        return _instance;
    }

    public static LocationHelper getInstance() {
        if (_instance == null) {
            throw new RuntimeException("could not ask for location helper instance without context");
        }
        return _instance;
    }

    private LocationHelper(Context context) {
        //init location tracker
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    public void getOneShotLocation(Context context, final SuccessCallback callback) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (callback == null) return;
                        if (!task.isSuccessful()) {
                            callback.onDone(false, "");
                            return;
                        }
                        callback.onDone(true, task.getResult());
                    }
                });
    }


    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                if (location != null) {
                    lastLocation = location;
                    //RLog.d(TAG, location.toString());
                }
            }
        }
    };

    public void start(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.requestLocationUpdates(new LocationRequest()
                            .setPriority(100)
                            .setInterval(1000),
                    mLocationCallback,
                    null /* Looper */);
        }
    }

    public void stop() {
        //remove location updates
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }


    public Location getLastKnownLocation() {
        return lastLocation;
    }
}
