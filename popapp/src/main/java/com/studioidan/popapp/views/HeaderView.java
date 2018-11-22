package com.studioidan.popapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.studioidan.pop_app.R;
import com.studioidan.popapp.utils.U;

public class HeaderView extends RelativeLayout implements View.OnClickListener {
    public static final String TAG = "HeaderView";

    //VIEWS
    public IconView iconActionOne, iconActionTwo, iconActionThree, hamburger;

    public TextView tvBackText, tvTitle, tvSubTitle;
    private RelativeLayout backHolder;

    //DATA
    private HeaderCallback callback;

    public HeaderView(Context context) {
        super(context);
        init();
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_header, this, true);

        hamburger = (IconView) findViewById(R.id.hamburgerIcon);
        backHolder = (RelativeLayout) findViewById(R.id.headerBackHolder);
        tvBackText = (TextView) findViewById(R.id.tvHeaderBackText);
        tvTitle = (TextView) findViewById(R.id.tvHeaderTitle);
        tvSubTitle = (TextView) findViewById(R.id.tvHeaderSubTitle);
        iconActionOne = (IconView) findViewById(R.id.headerActionOne);
        iconActionTwo = (IconView) findViewById(R.id.headerActionTwo);
        iconActionThree = (IconView) findViewById(R.id.headerActionThree);


        iconActionOne.setOnClickListener(this);
        iconActionTwo.setOnClickListener(this);
        iconActionThree.setOnClickListener(this);
        hamburger.setOnClickListener(this);
        backHolder.setOnClickListener(this);

        setHeaderMode(HEADER_MODE.REGULAR);
    }

    public void setCallback(HeaderCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.hamburgerIcon) {
            if (callback != null) callback.onHamburgerClicked();

        } else if (i == R.id.headerActionOne) {
            if (callback != null) callback.onActionOneClicked();

        } else if (i == R.id.headerActionTwo) {
            if (callback != null) callback.onActionTwoClicked();

        } else if (i == R.id.headerActionThree) {
            if (callback != null) callback.onActionThreeClicked();

        } else if (i == R.id.headerBackHolder) {
            if (callback != null) callback.onBackClicked();
        }
    }

    public void setHeaderMode(HEADER_MODE headerMode) {
        if (headerMode == HEADER_MODE.REGULAR) {
            setVisibility(VISIBLE, hamburger);
            setVisibility(GONE, backHolder);
        }

        if (headerMode == HEADER_MODE.BACK) {
            setVisibility(VISIBLE, backHolder, tvTitle);
            setVisibility(GONE, hamburger);
        }
    }

    public void setTitle(String title) {
        if (U.isEmpty(title)) {
            tvTitle.setVisibility(GONE);
            return;
        }
        tvTitle.setVisibility(VISIBLE);
        tvTitle.setText(title);
    }

    public void setSubTitle(String subTitle) {
        if (U.isEmpty(subTitle)) {
            tvSubTitle.setVisibility(GONE);
            return;
        }
        tvSubTitle.setVisibility(VISIBLE);
        tvSubTitle.setText(subTitle);
    }

    public void setText(String title, String subTitle) {
        setTitle(title);
        setSubTitle(subTitle);
    }

    public void setVisibility(int visibility, View... view) {
        for (View v : view) {
            v.setVisibility(visibility);
        }
    }

    public interface HeaderCallback {
        void onHamburgerClicked();

        void onActionOneClicked();

        void onActionTwoClicked();

        void onActionThreeClicked();

        void onBackClicked();
    }

    public IconView getActionIconOne() {
        return iconActionOne;
    }

    public IconView getActionIconTwo() {
        return iconActionTwo;
    }

    public IconView getActionIconThree() {
        return iconActionThree;
    }

    public enum HEADER_MODE {
        REGULAR, BACK
    }
}