package com.studioidan.popapp.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PopApp_laptop on 14/04/16.
 */
public class TArrayAdapter<T> extends ArrayAdapter<T> {
    protected ArrayList<T> data;
    protected Context context;
    protected LayoutInflater inflater;
    protected int layoutResource;

    public TArrayAdapter(ArrayList<T> data,Context context, int resource) {
        super(context, resource, (List<T>) data);
        this.data = (ArrayList<T>) data;
        this.context = context;
        this.layoutResource = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    protected void addItem(T item) {
        data.add(item);
        notifyDataSetChanged();
    }

    protected void setData(ArrayList<T> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T item = getItem(position);
        View v = convertView;
        if (convertView == null)
            v = inflater.inflate(layoutResource, null);

        return getFinalView(v, item, position);
    }


    protected View getFinalView(View layout, T item, int position) {
        return layout;
    }
}
