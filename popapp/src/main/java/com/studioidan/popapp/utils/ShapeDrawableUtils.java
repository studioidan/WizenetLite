package com.studioidan.popapp.utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class ShapeDrawableUtils {

    public static Drawable getRoundedShape(int color) {
        return getRoundedShape(color, GU.dpToPx(4), -1, -1);
    }

    public static Drawable getRoundedShape(int color, int radius) {
        return getRoundedShape(color, radius, -1, -1);
    }

    public static Drawable getRoundedShape(int color, int radius, int strokeWidth, int strokeColor) {
        GradientDrawable answer = new GradientDrawable();
        answer.setColor(color);
        answer.setCornerRadius(radius);

        if (strokeWidth != -1 && strokeColor != -1) {
            answer.setStroke(strokeWidth, strokeColor);
        }
        return answer;
    }

    public static Drawable getRoundedShape(int strokeColor, int topLeft, int topRight, int bottomRight, int bottomLeft) {

        GradientDrawable answer = new GradientDrawable();
        answer.setColor(strokeColor);
        float[] radii = {GU.dpToPx(topLeft), GU.dpToPx(topLeft),
                GU.dpToPx(topRight), GU.dpToPx(topRight),
                GU.dpToPx(bottomRight), GU.dpToPx(bottomRight),
                GU.dpToPx(bottomLeft), GU.dpToPx(bottomLeft)};

        answer.setCornerRadii(radii);

        return answer;
    }

    public static Drawable getStrokedShape(int strokeColor) {
        return getStrokedShape(strokeColor, GU.dpToPx(1));
    }

    public static Drawable getStrokedShape(int strokeColor, int strokeWidth) {
        return getButtonStroked(strokeColor, GU.dpToPx(strokeWidth), GU.dpToPx(4));
    }

    public static Drawable getButtonStroked(int strokeColor, int strokeWidth, int radius) {
        GradientDrawable answer = new GradientDrawable();
        answer.setColor(Color.TRANSPARENT);
        answer.setCornerRadius(radius);
        answer.setStroke(strokeWidth, strokeColor);
        return answer;
    }
}