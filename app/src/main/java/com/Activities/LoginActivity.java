package com.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.DatabaseHelper;
import com.GPSTracker;
import com.Helper;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.model.Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LoginActivity extends Activity {

    public static final String TAG = "Login Activity";
    private static final int REQUEST_PERMISSION_MULTIPLE = 300;
    private Helper helper;
    private DatabaseHelper db;

    private static final String marshmallowMacAddress = "02:00:00:00:00:00";
    private static final String fileAddressMac = "/sys/class/net/wlan0/address";

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    String memail, mpass, mac_address;
    private TextView sign_in, reset, view_url;
    private CheckBox login_checkbox_remember;
    EditText email, pass;
    Button write, read;
    GPSTracker gps = null;
    Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        permissionCheck();
        db = DatabaseHelper.getInstance(getApplicationContext());
        helper = new Helper();
        setContentView(R.layout.activity_login);
        boolean flag = helper.isNetworkAvailable(ctx);

        if (db.getValueByKey("AUTO_LOGIN").equals("1")) {
            goToMenu();
        }

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token = instanceIdResult.getToken();
                Log.d("token", token);
                // makeLogin(token);
            }
        });

//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayUseLogoEnabled(false);
//        actionBar.setDisplayHomeAsUpEnabled(false);
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(false);
//
//        ActionBar.LayoutParams lp1 = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
//        View customNav = LayoutInflater.from(this).inflate(R.layout.top_bar, null); // layout which contains your button.
//
//        actionBar.setCustomView(customNav, lp1);
//        Button iv = (Button) customNav.findViewById(R.id.back);
//        iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"clicked", Toast.LENGTH_LONG).show();
//            }
//        });

        if (flag) {
            //Toast.makeText(getApplicationContext(),"internet valid", Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(getApplicationContext(), "internet invalid", Toast.LENGTH_LONG).show();
            // try{
            //     goToOfflineFragment();
            // }

        }

        login_checkbox_remember = findViewById(R.id.login_checkbox_remember);
        login_checkbox_remember.setChecked(true);
        TextView login_forgotPassword = findViewById(R.id.login_forgotPassword);
        TextView update_url = findViewById(R.id.update_url);
        update_url.setPaintFlags(update_url.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        sign_in = findViewById(R.id.sign_in_button);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        try {
            if (!db.getValueByKey("username").equals("")) {
                email.setText(db.getValueByKey("username").toString());
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

        }

        login_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().Async_Wz_Forgot_Listener(helper.getMacAddr(getApplicationContext()), email.getText().toString(), new Model.Wz_Forgot_Listener() {
                    @Override
                    public void onResult(String str) {
                        Log.e("mytag", str.trim());
                        if (str.equals("0")) {
                            Toast.makeText(getApplicationContext(), "נשלח בהצלחה", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "כתובת לא נמצאה", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        update_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTopLogoImage();
                memail = email.getText().toString();
                mpass = pass.getText().toString();

                if (!checkEmail(memail)) {
                    Toast.makeText(getApplicationContext(), "invalid mail , try again", Toast.LENGTH_LONG).show();
                    return;
                }

                //Set logo image.
                FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        String token = instanceIdResult.getToken();
                        Log.d("token", token);
                        makeLogin(token);
                    }
                });
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        setTopLogoImage();
    }

    private void initMacAddress() {
        mac_address = helper.getMacAddr(getApplicationContext());
    }

    private void makeLogin(String token) {
        //Toast.makeText(getApplicationContext(),"mac_address:" + mac_address, Toast.LENGTH_LONG).show();
        try {
            Model.getInstance().AsyncLogin(mac_address, memail, mpass, token, new Model.LoginListener() {
                @Override
                public void onResult(String str) {
                    if (str.equals("incorrect")) {
                        Toast.makeText(getApplicationContext(), "incorrect URL", Toast.LENGTH_LONG).show();
                    } else if (str.equals("1")) {
                        //chk if is not the same user,
                        //if is not the same user - delete files because they not belong him.
                        if (!db.getValueByKey("username").toString().equals(memail)) {
                            helper.deleteAllFiles();
                        }
                        if (login_checkbox_remember.isChecked()) {
                            db.updateValue("username", memail);
                            db.updateValue("AUTO_LOGIN", "1");

                        }
                        goToMenu();

                    } else {
                        Toast.makeText(getApplicationContext(), "username or password incorrect", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * TODO move this method to the MainActivity and save it somewhere so there won't be a need to download the image on every access
     * Sets the top image to the user's company logo.
     */
    private void setTopLogoImage() {
        try {
            String userUrl = DatabaseHelper.getInstance(getApplicationContext()).getValueByKey("URL");
            String logoUrl = userUrl + "/data/logo.png";
            Picasso.get().load(logoUrl).into((ImageView) findViewById(R.id.login_topLogo));
            // Picasso.
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    private void goToMenu() {
        try {
            Model.getInstance().Async_Wz_getProjects_Listener(mac_address, new Model.Wz_getProjects_Listener() {
                @Override
                public void onResult(String str) {
                    if (!str.contains("error")) {
                        //Toast.makeText(getApplicationContext(),"success load project", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "error load project", Toast.LENGTH_LONG).show();

                    }
                }
            });
            Model.getInstance().Async_Wz_getTasks_Listener(mac_address, new Model.Wz_getTasks_Listener() {
                @Override
                public void onResult(String str) {
                    if (!str.contains("error")) {
                        //Toast.makeText(getApplicationContext(),"success load tasks", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "error load tasks", Toast.LENGTH_LONG).show();

                    }
                }
            });
        } catch (Exception e) {
            helper.LogPrintExStackTrace(e);
        }

        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //startActivityForResult(intent, 1);
        
        startActivity(intent);
    }

    private boolean permissionCheck() {
        int storagePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPhoneStatePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        int gpsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int sms = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        List<String> listPermissionsNeeded = new ArrayList<String>();

        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (readPhoneStatePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (gpsPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (sms != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(LoginActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_PERMISSION_MULTIPLE);
            return false;
        }

        initMacAddress();
        return true;
    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Toast.makeText(getApplicationContext(), String.valueOf(requestCode), Toast.LENGTH_LONG).show();
//        if (requestCode == 1) {
//            if(resultCode == Activity.RESULT_OK){
//                String result=data.getStringExtra("result");
//                Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
//                if (result == "close"){
//                    finish();
//                }
//            }
//            if (resultCode == Activity.RESULT_CANCELED) {
//                Toast.makeText(getApplicationContext(),"hello", Toast.LENGTH_LONG).show();
//                //Write your code if there's no result
//            }
//        }
//    }//onActivityResult

    //###################################
//CHECK EMAIL
//###################################
    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return (super.onOptionsItemSelected(item));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_PERMISSION_MULTIPLE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Permission was granted!");
                initMacAddress();
            } else {
                Log.e(TAG, "Permission was not granted!");
                Toast.makeText(LoginActivity.this, "Permission must be granted", Toast.LENGTH_SHORT).show();
                LoginActivity.this.finish();
            }
        }

    }


}
