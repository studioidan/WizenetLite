package com.studioidan.popapp.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by PopApp_laptop on 01/01/2016.
 */
public class FontTextView extends TextView {
    private Typeface mTypeface;
    private String fontName = "default";

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs, context);
    }

    private void init(AttributeSet attrs, Context context) {
        BaseFontView.setFont(this,attrs,context);
    }
}
