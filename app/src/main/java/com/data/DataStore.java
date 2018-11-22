package com.data;

import android.content.Context;

import com.Classes.Call;
import com.Keys;
import com.google.gson.reflect.TypeToken;
import com.studioidan.popapp.utils.CPM;

import java.util.ArrayList;

public class DataStore {

    private static DataStore instance = null;


    public static DataStore getInstance(Context context) {
        if (instance == null) instance = new DataStore(context);
        return instance;
    }

    public static DataStore getInstance() {
        if (instance == null) instance = new DataStore(null);
        return instance;
    }

    public DataStore(Context context) {
        if (context == null) return;

        // load data from memory
        calls = (ArrayList<Call>) CPM.getArrayObject(Keys.CALLS, new TypeToken<ArrayList<Call>>() {
        }.getType(), context);


    }


    /**********      Calls      ************/
    private ArrayList<Call> calls;

    public ArrayList<Call> getCalls() {
        if (calls == null) calls = new ArrayList<>();
        return calls;
    }

    public Call getCall(int id) {
        for (Call call : getCalls()) {
            if (call.getCallID() == id) return call;
        }
        return null;
    }

    public boolean saveCalls(Context context) {
        return CPM.putObject(Keys.CALLS, getCalls(), context);
    }


}
