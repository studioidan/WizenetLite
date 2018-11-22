package com.studioidan.popapp.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.studioidan.pop_app.R;
import com.studioidan.popapp.utils.U;


public class DialogGenericInput extends DialogFragment implements View.OnClickListener {

    public static final String ARG_TITLE = "arg.title";
    public static final String ARG_VALUE = "arg.value";
    public static final String ARG_BTN_OK_TEXT = "arg.btn.ok.text";
    public static final String ARG_CANCEL_TEXT = "arg.btn.cancel.text";
    public static final String EXTRA_VALUE = "extra.value";

    //VIEWS
    private Button btnSave, btnCancel;
    private EditText etValue;
    private TextView tvTitle;

    //DATA
    private String value;
    private String title;
    private String btnOkText;
    private String btnCancelText;

    public static DialogGenericInput getInstance(String title) {
        return getInstance(title, "");
    }

    public static DialogGenericInput getInstance(String title, String value) {
        return getInstance(title, value, "Save", "Cancel");
    }


    public static DialogGenericInput getInstance(String title, String value, String btnOkText, String btnCancelText) {
        DialogGenericInput answer = new DialogGenericInput();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        bundle.putString(ARG_VALUE, value);
        bundle.putString(ARG_BTN_OK_TEXT, btnOkText);
        bundle.putString(ARG_CANCEL_TEXT, btnCancelText);
        answer.setArguments(bundle);
        return answer;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(ARG_TITLE);
        value = getArguments().getString(ARG_VALUE);
        btnOkText = getArguments().getString(ARG_BTN_OK_TEXT);
        btnCancelText = getArguments().getString(ARG_CANCEL_TEXT);
    }

    @Override
    public void onStart() {
        super.onStart();
        // safety check
        if (getDialog() == null) {
            return;
        }
        // set the animations to use on showing and hiding the dialog
        //getDialog().getWindow().setWindowAnimations(R.style.DialogAnimation);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.generic_input_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        etValue = (EditText) view.findViewById(R.id.etInput);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);

        tvTitle.setText(title);
        etValue.setText(value);
        btnSave.setText(btnOkText);
        btnCancel.setText(btnCancelText);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            value = U.value(etValue);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, new Intent().putExtra(EXTRA_VALUE, U.value(etValue)));
            dismiss();
            return;
        }
        if (v.getId() == R.id.btnCancel) {
            dismiss();
        }
    }
}
