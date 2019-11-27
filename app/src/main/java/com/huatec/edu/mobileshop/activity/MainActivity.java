package com.huatec.edu.mobileshop.activity;



import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.huatec.edu.mobileshop.R;
import  com.huatec.edu.mobileshop.common.BaseActivity;
import com.huatec.edu.mobileshop.fragment.NavigationFragment;

public class MainActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.f1_main,new NavigationFragment());
        transaction.commit();
    }
}
