package com.studioidan.popapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.studioidan.popapp.interfaces.GenericCallback;
import com.studioidan.popapp.interfaces.YesNoCallback;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static android.util.TypedValue.COMPLEX_UNIT_IN;
import static android.util.TypedValue.COMPLEX_UNIT_MM;
import static android.util.TypedValue.COMPLEX_UNIT_PT;
import static android.util.TypedValue.COMPLEX_UNIT_PX;
import static android.util.TypedValue.COMPLEX_UNIT_SP;

/**
 * Created by PopApp_laptop on 02/12/2015.
 */
public class U {

    public static int getScreenHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //int width = size.x;
        int height = size.y;
        return height;
    }

    public static int getScreenWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        //int height = size.y;
        return width;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isEmpty(String... strings) {
        for (String str : strings) {
            if (!isEmpty(str))
                return false;
        }
        return true;
    }

    public static boolean isEmpty(EditText editText) {
        return editText == null || isEmpty(editText.getText().toString());
    }

    public static boolean isEmpty(TextView tv) {
        return tv == null || isEmpty(tv.getText().toString());
    }

    public static boolean isOneEmpty(EditText... editTexts) {
        for (EditText et : editTexts) {
            if (isEmpty(et))
                return true;
        }
        return false;
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(ArrayList arrayList) {
        return arrayList == null || arrayList.size() == 0;
    }

    public static boolean isEmpty(ImageView imageView) {
        return imageView.getDrawable() == null;
    }

    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String value(EditText editText) {
        return isEmpty(editText) ? "" : editText.getText().toString();
    }

    public static String value(TextView tv) {
        return isEmpty(tv) ? "" : tv.getText().toString();
    }

    public static void okDialog(Context context, String title, String message) {
        okDialog(context, title, message, context.getString(android.R.string.yes), null);
    }

    public static void okDialog(Context context, String title, String message, String buttonText) {
        okDialog(context, title, message, buttonText, null);
    }

    public static void okDialog(Context context, String title, String message, String buttonText, final GenericCallback callback) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (callback != null) callback.onDone(true, "");
                    }
                })
                .show();
    }

    public static void yesNoDialog(Context con, String title, String message, String positiveText, String negativeText, final YesNoCallback callback) {
        try {
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(con);

            builder.setTitle(title);
            builder.setMessage(message);

            builder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (callback != null)
                        callback.onYes(null);
                }
            });
            builder.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (callback != null)
                        callback.onNo(null);
                }
            });
            android.support.v7.app.AlertDialog alert = builder.create();
            alert.show();
        } catch (Exception ex) {
        }
    }

    /**
     * this method checks for permission and returns true if permission already granted
     * and false if not (also makes the request for you)
     */
    public static boolean checkForPermission(Activity activity, String Permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(activity, Permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Permission}, requestCode);
            return false;
        } else {
            return true;
        }
    }

    public static int inchesToPixels(Context context, float inches) {
        int answer = (int) changeDimension(context, TypedValue.COMPLEX_UNIT_IN, inches);
        return answer;
    }

    public static int mmToPixels(Context context, int mm) {
        int answer = (int) changeDimension(context, TypedValue.COMPLEX_UNIT_MM, mm);
        return answer;
    }

    public static float changeDimension(Context context, int unit, float value) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        switch (unit) {
            case COMPLEX_UNIT_PX:
                return value;
            case COMPLEX_UNIT_DIP:
                return value * metrics.density;
            case COMPLEX_UNIT_SP:
                return value * metrics.scaledDensity;
            case COMPLEX_UNIT_PT:
                return value * metrics.xdpi * (1.0f / 72);
            case COMPLEX_UNIT_IN:
                return value * metrics.xdpi;
            case COMPLEX_UNIT_MM:
                return value * metrics.xdpi * (1.0f / 25.4f);
        }
        return 0;
    }

    public static boolean writeToFile(File file, String content) {
        try {
            FileWriter out = new FileWriter(file);
            out.write(content);
            out.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public static String readFileFromAssets(Context context, String fileName) {
        try {
            StringBuilder buf = new StringBuilder();
            InputStream json = context.getAssets().open(fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
            in.close();
            return buf.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static int getLocationInWindowX(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        return x;
    }

    public static int getLocationInWindowY(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        return y;
    }
}
