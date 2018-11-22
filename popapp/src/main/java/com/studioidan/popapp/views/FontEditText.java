package com.studioidan.popapp.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;


/**
 * Created by PopApp_laptop on 01/01/2016.
 */
public class FontEditText extends EditText {
    private String fontName;
    private Typeface mTypeface = null;


    public FontEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, context);
    }

    public FontEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, context);
    }

    private void init(AttributeSet attrs, Context context) {
        BaseFontView.setFont(this,attrs,context);
    }
}
