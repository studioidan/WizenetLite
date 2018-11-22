package com.studioidan.popapp.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class Tadapter<T> extends BaseAdapter {
    protected ArrayList<T> data;
    protected Context context;
    protected LayoutInflater inflater;
    protected int layoutResource;

    public Tadapter(ArrayList<T> data, Context con, int layoutResource) {
        this.data = data;
        this.context = con;
        this.layoutResource = layoutResource;
        inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        T item = data.get(i);
        View v = view;
        if (view == null)
            v = inflater.inflate(layoutResource, null);

        return getFinalView(v, item, i);
    }

    protected void addItem(T item) {
        data.add(item);
        notifyDataSetChanged();
    }

    protected void setData(ArrayList<T> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    protected View getFinalView(View layout, T item, int position) {
        return layout;
    }
}