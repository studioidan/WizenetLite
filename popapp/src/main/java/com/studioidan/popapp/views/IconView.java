package com.studioidan.popapp.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.studioidan.pop_app.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by PopApp_laptop on 01/01/2016.
 */
public class IconView extends AppCompatTextView {

    static Typeface mTypefaceAwesomeFont, mTypefaceMaterialIcons, mTypefaceIcoFont;

    private static final int ICON_FONT_AWESOME = 0;
    private static final int ICON_MATERIAL = 1;
    private static final int ICON_ICO_FONT = 2;

    private int selectedFont = 0;

    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, context);
    }

    private void init(AttributeSet attrs, Context context) {
        String iconCode;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.IconView, 0, 0);
        try {
            iconCode = ta.getString(R.styleable.IconView_icon_code);
            selectedFont = ta.getInt(R.styleable.IconView_type, 0);
        } finally {
            ta.recycle();
        }

        if (isInEditMode()) {
            setText(getSelectedFontLabel());
            return;
        }

        setText(iconCode);

        if (mTypefaceAwesomeFont == null)
            mTypefaceAwesomeFont = getFontFromRes(R.raw.font_awesome, context);

        if (mTypefaceMaterialIcons == null)
            mTypefaceMaterialIcons = getFontFromRes(R.raw.material_icons, context);

        if (mTypefaceIcoFont == null)
            mTypefaceIcoFont = getFontFromRes(R.raw.icofont, context);

        this.setTypeface(getSelectedTypeface());
    }

    private Typeface getSelectedTypeface() {
        switch (selectedFont) {
            case ICON_FONT_AWESOME:
                return mTypefaceAwesomeFont;
            case ICON_ICO_FONT:
                return mTypefaceIcoFont;
            case ICON_MATERIAL:
                return mTypefaceMaterialIcons;
        }
        return mTypefaceAwesomeFont;

    }

    private String getSelectedFontLabel() {
        switch (selectedFont) {
            case ICON_FONT_AWESOME:
                return "FA";
            case ICON_MATERIAL:
                return "M";
            case ICON_ICO_FONT:
                return "IF";
        }

        return "NA";
    }

    protected Typeface getFontFromRes(int resource, Context context) {
        Typeface tf = null;
        InputStream is = null;
        try {
            is = getResources().openRawResource(resource);
        } catch (Resources.NotFoundException e) {
            Log.e("getFontFromRes", "Could not find font_awesome in resources!");
        }

        String outPath = context.getCacheDir() + "/tmp" + System.currentTimeMillis() + ".raw";

        try {
            byte[] buffer = new byte[is.available()];
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outPath));

            int l = 0;
            while ((l = is.read(buffer)) > 0)
                bos.write(buffer, 0, l);

            bos.close();
            tf = Typeface.createFromFile(outPath);
            // clean up
            new File(outPath).delete();
        } catch (IOException e) {
            Log.e("getFontFromRes", "Error reading in font_awesome!");
            return null;
        }
        Log.d("getFontFromRes", "Successfully loaded font_awesome.");
        return tf;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }


    public void setIcon(String icon, int iconType) {
        if (this.selectedFont != iconType) {
            this.selectedFont = iconType;
            setTypeface(getSelectedTypeface());
        }

        setText(icon);
    }

    public static final String FONT_AWESOME_TRASH = "\uf014";
    public static final String FONT_AWESOME_ADD = "\uf055";
    public static final String FONT_AWESOME_VI = "\uf058";
    public static final String MATERIAL_TRASH = "\uf111";
}
