package com.studioidan.popapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.studioidan.pop_app.R;
import com.studioidan.popapp.utils.U;

public class BaseFontView {

    public static void setFont(TextView fontView, AttributeSet attrs, Context context) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BaseFontView, 0, 0);
        try {
            String fontName = ta.getString(R.styleable.BaseFontView_fontName);
            if (U.isEmpty(fontName)) fontName = "default_font";
            Typeface mTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName + ".ttf");
            fontView.setTypeface(mTypeface);
        } finally {
            ta.recycle();
        }
    }
}
