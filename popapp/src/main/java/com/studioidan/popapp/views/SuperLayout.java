package com.studioidan.popapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.studioidan.pop_app.R;
import com.studioidan.popapp.utils.GU;

/**
 * Created by PopApp_laptop on 18/01/18.
 */

public class SuperLayout extends LinearLayout {
    private Context context;

    public SuperLayout(Context context) {
        super(context);
        init(context, null);
    }

    public SuperLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public SuperLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SuperLayout, 0, 0);
        try {

            int bgColor = ta.getColor(R.styleable.SuperLayout_slBackgroundColor, 1);

            //stroke
            int strokeColor = ta.getColor(R.styleable.SuperLayout_slStrokeColor, -1);
            int strokeWidth = ta.getDimensionPixelSize(R.styleable.SuperLayout_slStrokeWidth, -1);

            //corners
            int cornerRadiusTopLeft = ta.getDimensionPixelSize(R.styleable.SuperLayout_slCornerRadiusTopLeft, 0);
            int cornerRadiusTopRight = ta.getDimensionPixelSize(R.styleable.SuperLayout_slCornerRadiusTopRight, 0);
            int cornerRadiusBottomRight = ta.getDimensionPixelSize(R.styleable.SuperLayout_slCornerRadiusBottomRight, 0);
            int cornerRadiusBottomLeft = ta.getDimensionPixelSize(R.styleable.SuperLayout_slCornerRadiusBottomLeft, 0);
            int cornerRadiusBottomAll = ta.getDimensionPixelSize(R.styleable.SuperLayout_slCornerRadiusAll, -1);

            GradientDrawable drawable = new GradientDrawable();

            if (bgColor != 1) {
                drawable.setColor(bgColor);
            }

            if (strokeColor != -1 && strokeWidth != -1) {
                drawable.setStroke(strokeWidth, strokeColor);
            }

            if (cornerRadiusBottomAll != -1) {
                float[] radii = {cornerRadiusBottomAll, cornerRadiusBottomAll, cornerRadiusBottomAll, cornerRadiusBottomAll, cornerRadiusBottomAll, cornerRadiusBottomAll, cornerRadiusBottomAll, cornerRadiusBottomAll};
                drawable.setCornerRadii(radii);
            } else {
                float[] radii = {cornerRadiusTopLeft, cornerRadiusTopLeft, cornerRadiusTopRight, cornerRadiusTopRight, cornerRadiusBottomRight, cornerRadiusBottomRight, cornerRadiusBottomLeft, cornerRadiusBottomLeft};
                drawable.setCornerRadii(radii);
            }

            setBackground(drawable);
        } finally {
            ta.recycle();
        }
    }


    public static Drawable getBackground(int color, int topLeft, int topRight, int bottomRight, int bottomLeft) {
        GradientDrawable answer = new GradientDrawable();
        answer.setColor(color);
        float[] radii = {GU.dpToPx(topLeft), GU.dpToPx(topLeft), GU.dpToPx(topRight), GU.dpToPx(topRight), GU.dpToPx(bottomRight), GU.dpToPx(bottomRight), GU.dpToPx(bottomLeft), GU.dpToPx(bottomLeft)};
        answer.setCornerRadii(radii);
        return answer;
    }
}
