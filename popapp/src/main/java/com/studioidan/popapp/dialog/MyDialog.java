package com.studioidan.popapp.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.studioidan.pop_app.R;
import com.studioidan.popapp.interfaces.BaseDialogCallback;
import com.studioidan.popapp.utils.U;


/**
 * Created by PopApp_laptop on 19/04/18.
 */

public class MyDialog extends BaseDialog implements View.OnClickListener {

    //DATA
    private String title, btnPositiveText, btnNegativeText;
    private BaseDialogCallback callback;


    //VIEWS
    private Button btnNegative, btnPositive;
    private TextView tvTitle;


    public MyDialog(Context context, String title, String btnPositiveText) {
        this(context, title, btnPositiveText, null, null);
    }

    public MyDialog(Context context, String title, String btnPositiveText, String btnNegativeText) {
        this(context, title, btnPositiveText, btnNegativeText, null);
    }

    public MyDialog(Context context, String title, String btnPositiveText, String btnNegativeText, BaseDialogCallback callback) {
        super(context);

        this.title = title;
        this.btnPositiveText = btnPositiveText;
        this.btnNegativeText = btnNegativeText;
        this.callback = callback;

        this.setContentView(R.layout.my_dialog);

        tvTitle = findViewById(R.id.tvTitle);
        btnNegative = findViewById(R.id.btnNegative);
        btnPositive = findViewById(R.id.btnPositive);

        btnPositive.setOnClickListener(this);
        btnNegative.setOnClickListener(this);

        btnNegative.setVisibility(U.isEmpty(btnNegativeText) ? View.GONE : View.VISIBLE);

        //update ui
        tvTitle.setText(this.title);
        btnPositive.setText(this.btnPositiveText);
        btnNegative.setText(this.btnNegativeText);
    }

    @Override
    public void onClick(View view) {
        dismiss();
        if (view.getId() == R.id.btnPositive) {
            if (callback != null) callback.onNegativeClicked();
        } else if (view.getId() == R.id.btnNegative) {
            if (callback != null) callback.onPositiveClicked();
        }
    }
}
