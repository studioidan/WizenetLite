package com.studioidan.popapp.dialog;

import android.graphics.Point;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by PopApp_laptop on 30/05/17.
 */

public class BaseDialogFragment extends DialogFragment {

    protected static float WIDTH_PERCENTS = 0.75f;

    /**
     * make width 3/4 percents
     */
    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        window.setLayout((int) (size.x * WIDTH_PERCENTS), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        super.onResume();
    }
}
