package com.studioidan.popapp.utils;

import android.content.res.Resources;

/**
 * Created by PopApp_laptop on 02/12/2015.
 */
public class GU {
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
