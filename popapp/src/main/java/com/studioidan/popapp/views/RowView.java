package com.studioidan.popapp.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.studioidan.pop_app.R;
import com.studioidan.popapp.utils.U;

/**
 * Created by PopApp_laptop on 14/11/16.
 */

public class RowView extends RelativeLayout {
    //DATA
    //private Context context;

    //VIEWS
    private TextView tvTitle, tvSubTitle;
    private IconView iconStart, iconEndOne, iconEndTwo;
    private RelativeLayout startContainer, widgetHolder;
    private LinearLayout endContainer;
    private SwitchCompat mSwitch;

    public RowView(Context context) {
        super(context);
        init(context, null);
    }

    public RowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //this.context = context;
        LayoutInflater.from(context).inflate(R.layout.row_view, this, true);

        tvTitle = (TextView) findViewById(R.id.tvRowTitle);
        tvSubTitle = (TextView) findViewById(R.id.tvRowSubTitle);
        iconStart = (IconView) findViewById(R.id.rowIconStart);
        iconEndOne = (IconView) findViewById(R.id.rowIconEndOne);
        iconEndTwo = (IconView) findViewById(R.id.rowIconEndTwo);

        startContainer = (RelativeLayout) findViewById(R.id.startHolder);
        endContainer = (LinearLayout) findViewById(R.id.endHolder);
        widgetHolder = (RelativeLayout) findViewById(R.id.widgetHolder);
        mSwitch = (SwitchCompat) findViewById(R.id.rowViewSwitch);

       /* if (isInEditMode()) {
            setText("Title", "SubTitle");
            return;
        }*/
        if (attrs != null) initAttrs(attrs);

    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RowView, 0, 0);
        try {

            int rowMode = ta.getInt(R.styleable.RowView_row_mode, 0);
            setRowMode(rowMode);

            if (isInEditMode()) {
                setText("Title", "SubTitle");
                return;
            }

            String title = ta.getString(R.styleable.RowView_row_title);
            String sub_title = ta.getString(R.styleable.RowView_row_sub_title);

            String startIcon = ta.getString(R.styleable.RowView_left_icon);
            String endIconOne = ta.getString(R.styleable.RowView_right_icon_one);
            String endIconTwo = ta.getString(R.styleable.RowView_right_icon_two);

            boolean showRightArrow = ta.getBoolean(R.styleable.RowView_show_right_arrow, false);
            setText(title, sub_title);
            setStartIcon(startIcon);
            setEndIconOne(endIconOne);
            setEndIconTwo(endIconTwo);

            if (showRightArrow) showRightArrow();
        } finally {
            ta.recycle();
        }

    }

    public TextView getTitle() {
        return tvTitle;
    }

    public TextView getSubTitle() {
        return tvSubTitle;
    }

    public void setText(String title, String subTitle) {
        setTitle(title);
        setSubTitle(subTitle);
    }

    public void setTitle(String title) {
        if (U.isEmpty(title)) {
            tvTitle.setVisibility(GONE);
            return;
        }
        tvTitle.setVisibility(VISIBLE);
        tvTitle.setText(title);
    }

    public void setSubTitle(String str) {
        if (U.isEmpty(str)) {
            tvSubTitle.setVisibility(GONE);
            return;
        }
        tvSubTitle.setVisibility(VISIBLE);
        tvSubTitle.setText(str);
    }

    public void setStartIcon(String res) {
        if (U.isEmpty(res)) {
            iconStart.setVisibility(GONE);
            return;
        }
        startContainer.setVisibility(VISIBLE);
        iconStart.setVisibility(VISIBLE);
        iconStart.setText(res);
    }

    public IconView getEndIconOne() {
        return iconEndOne;
    }

    public IconView getEndIconTwo() {
        return iconEndTwo;
    }

    public void setEndIconOne(String res) {
        iconEndOne.setVisibility(U.isEmpty(res) ? GONE : VISIBLE);
        iconEndOne.setText(res);
    }

    public void setEndIconTwo(String res) {
        iconEndTwo.setVisibility(U.isEmpty(res) ? GONE : VISIBLE);
        iconEndTwo.setText(res);
    }

    public void showRightArrow() {
        endContainer.setVisibility(VISIBLE);
    }

    private void setRowMode(int rowMode) {
        widgetHolder.setVisibility(rowMode == 0 ? GONE : VISIBLE);
        mSwitch.setVisibility(rowMode == 1 ? VISIBLE : GONE);
    }

    public void setSwitchListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        mSwitch.setOnCheckedChangeListener(onCheckedChangeListener);
    }

}
