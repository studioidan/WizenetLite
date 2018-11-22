package com.studioidan.popapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class SquareLinearLayout extends LinearLayout {

    public SquareLinearLayout(Context context) {
        super(context);
    }

    public SquareLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(size, size);
    }
}