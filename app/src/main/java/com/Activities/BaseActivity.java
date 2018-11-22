package com.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.data.DataStore;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int TRANSACTION_ANIM_TYPE_NONE = 0;
    public static final int TRANSACTION_ANIM_SWIPE = 1;

    public DataStore ds = DataStore.getInstance();
    protected ProgressDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {

    }


    public void replaceFragment(int containerId, Fragment fragment, String tag) {
        replaceFragment(containerId, fragment, fragment.getClass().getSimpleName(), false, null);
    }

    public void replaceFragment(int containerId, Fragment fragment, String tag, boolean addToBackStack, String backStackTag) {
        replaceFragment(containerId, fragment, tag, addToBackStack, backStackTag, TRANSACTION_ANIM_SWIPE);
    }

    public void replaceFragment(int containerId, Fragment fragment, String tag, boolean addToBackStack, String backStackTag, int animType) {
        if (animType == TRANSACTION_ANIM_TYPE_NONE) {
            replaceFragment(containerId, fragment, tag, addToBackStack, backStackTag, -1, -1, -1, -1);
            return;
        }

        //default is swipe to left
        replaceFragment(containerId, fragment, tag, addToBackStack, backStackTag, R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
    }


    public void replaceFragment(int containerId, Fragment fragment, String tag, boolean addToBackStack, String backStackTag, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (enter != -1 && exit != -1) {
            if (popEnter != -1 && popExit != -1) {
                fragmentTransaction.setCustomAnimations(enter, exit, popEnter, popExit);
            } else {
                fragmentTransaction.setCustomAnimations(enter, exit);
            }
        }

        fragmentTransaction.replace(containerId, fragment, tag);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(backStackTag);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * Quick fragment replace transaction
     */
    public void addFragment(int containerId, Fragment fragment, String tag, boolean addToBackStack, String backStackTag) {
        addFragment(containerId, fragment, tag, addToBackStack, backStackTag, -1, -1, -1, -1);
    }

    /**
     * Quick fragment replace transaction
     */
    public void addFragment(int containerId, Fragment fragment, String tag, boolean addToBackStack, String backStackTag, int enter, int exit, int popEnter, int popExit) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (enter != -1 && exit != -1) {
            if (popEnter != -1 && popExit != -1) {
                fragmentTransaction.setCustomAnimations(enter, exit, popEnter, popExit);
            } else {
                fragmentTransaction.setCustomAnimations(enter, exit);
            }
        }

        fragmentTransaction.add(containerId, fragment, tag);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(backStackTag);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public Fragment findFragment(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    public Fragment findFragmentInContainer(int containerId) {
        return (getSupportFragmentManager().findFragmentById(containerId));
    }
}
