package com.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.Activities.BaseActivity;
import com.Activities.R;
import com.data.DataStore;
import com.studioidan.popapp.utils.U;

public class BaseFragment extends Fragment implements View.OnClickListener {

    protected BaseActivity activity;
    // protected Logger logger;
    protected DataStore ds;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
        ds = DataStore.getInstance();
        // logger = Logger.getInstance(context.getApplicationContext());

    }

    @Override
    public void onClick(View view) {

    }

    /**
     * Quick fragment replace transaction
     */
    public void replaceFragment(int containerId, Fragment fragment, String tag) {
        ((BaseActivity) getActivity()).replaceFragment(containerId, fragment, fragment.getClass().getSimpleName());
    }

    /**
     * Quick fragment replace transaction
     */
    public void replaceFragment(int containerId, Fragment fragment, String tag, boolean addToBackStack, String backStackTag) {
        ((BaseActivity) getActivity()).replaceFragment(containerId, fragment, tag, addToBackStack, backStackTag);
    }

    /**
     * Quick fragment replace transaction
     */
    public void replaceFragment(int containerId, Fragment fragment, String tag, boolean addToBackStack, String backStackTag, int enter, int exit, int popEnter, int popExit) {
        ((BaseActivity) getActivity()).replaceFragment(containerId, fragment, tag, addToBackStack, backStackTag, enter, exit, popEnter, popExit);

    }

    public void addFragment(int containerId, Fragment fragment, String tag, boolean addToBackStack, String backStackTag) {
        addFragment(containerId, fragment, tag, addToBackStack, backStackTag, -1, -1, -1, -1);
    }

    public void addFragment(int containerId, Fragment fragment, String tag, boolean addToBackStack, String backStackTag, int enter, int exit, int popEnter, int popExit) {
        ((BaseActivity) getActivity()).addFragment(containerId, fragment, tag, addToBackStack, backStackTag, enter, exit, popEnter, popExit);
    }

    protected void showError(String message) {
        U.okDialog(getActivity(), getString(R.string.error), message);
    }

    protected int color(int color) {
        return ContextCompat.getColor(getActivity(), color);
    }
}
