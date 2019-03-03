package com.util;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class FLogger {

    public static String username = "";

    public static final String TAG = "F_LOGGER";

    public static void log_i(String log) {
        log(LogType.INFO, log);
    }

    public static void log_e(String log) {
        log(LogType.ERROR, log);
    }

    public static void log_w(String log) {
        log(LogType.WARNING, log);
    }

    public static void log_s(String log) {
        log(LogType.SUCCESS, log);
    }

    public static void log_d(String log) {
        log(LogType.DEBUG, log);
    }

    private static void log(int logType, String log) {
        Log.d(TAG, log);

        HashMap<String, Object> map = new HashMap<>();
        map.put("log", log);
        map.put("logType", logType);
        map.put("time", System.currentTimeMillis());
        map.put("user", username);

        FirebaseFirestore.getInstance().collection("logs").document("" + System.currentTimeMillis()).set(map);
    }

    public class LogType {
        static final int INFO = 0;
        static final int ERROR = 1;
        static final int WARNING = 2;
        static final int SUCCESS = 3;
        static final int DEBUG = 4;
    }
}
