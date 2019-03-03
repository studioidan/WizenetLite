package com.server;

import android.content.Context;
import android.util.Log;

import com.DatabaseHelper;
import com.Helper;
import com.studioidan.httpagent.HttpAgent;
import com.studioidan.httpagent.JsonArrayCallback;
import com.studioidan.httpagent.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServerManager {
    public static final String TAG = "ServerManager";


    public static String getBaseUrl(Context context) {
        String answer = DatabaseHelper.getInstance(context).getValueByKey("URL");
        return answer;
    }

    public static void getCalls(Context context) {
        JSONObject o = null;
        try {
            o = new JSONObject();
            o.put("name", "koko");
            o.put("age", 13);
            o.put("hobbies", "[basketball,gaming]");
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            return;
        }

        String b = getBaseUrl(context);
        HttpAgent.post(b + "WIZEAPI/")
                .queryParams("func", "getCalls")
                .withBody("data=" + o.toString())
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .goJsonArray(new JsonArrayCallback() {
                    @Override
                    protected void onDone(boolean success, JSONArray jsonResults) {
                        Log.d(TAG, "s");
                    }
                });

    }

    private static String getUrl() {
        return "";
    }


    public static void startWork(Context context, String callId, String lat, String lon) {
        JSONObject o;
        try {
            o = new JSONObject();
            o.put("callId", callId);
            o.put("lat", lat);
            o.put("lon", lon);
            o.put("mac", Helper.getMacAddr(context));
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            return;
        }

        String b = getBaseUrl(context);
        HttpAgent.post(b + "WIZEAPI/")
                .queryParams("func", "startWork")
                .withBody("data=" + o.toString())
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .goString(new StringCallback() {
                    @Override
                    protected void onDone(boolean success, String stringResults) {
                        Log.d(TAG, "s");
                    }
                });

    }

    public static void logCallTimeTable(Context context, JSONArray callTimeTable) {
        JSONObject o;
        //http://main.wizenet.co.il/
        String b = getBaseUrl(context);
        HttpAgent.post(b + "WIZEAPI/")
                .queryParams("func", "logCallTimeTable")
                .withBody("data=" + callTimeTable.toString())
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .goString(new StringCallback() {
                    @Override
                    protected void onDone(boolean success, String stringResults) {
                        Log.d(TAG, "s");
                    }
                });

    }
}
