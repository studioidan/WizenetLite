package com;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import com.data.DataStore;

public class App extends Application {
    public static final String TAG = "MIP & GO APP";

    public static final String NOTIFICATION_CHANNEL = "mip and go notification channel";

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        DataStore.getInstance(getApplicationContext());
        //Logger.getInstance(getApplicationContext());
        //createNotificationChannel();
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL,
                    "Mip&Go Service Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "on terminate");
    }
}
