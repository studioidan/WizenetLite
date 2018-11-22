package com.studioidan.popapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by PopApp_laptop on 19/04/18.
 */

public class BaseDialog extends Dialog {
    protected static float WIDTH_PERCENTS = 0.75f;

    public BaseDialog(@NonNull Context context) {
        super(context);
    }


    /**
     * make width 3/4 percents
     */
    @Override
    protected void onStart() {
        Window window = getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        window.setLayout((int) (size.x * WIDTH_PERCENTS), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        super.onStart();
    }

}
