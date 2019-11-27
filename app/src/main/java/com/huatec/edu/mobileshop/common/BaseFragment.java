package com.huatec.edu.mobileshop.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private Unbinder mUnbinder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewId(), container, false);
        mUnbinder = ButterKnife.bind(this,view);
        initView(view);
        initData();
        return view;
    }
    protected void initData() {
    }

    protected void initView(View view) {
    }
    public abstract int getContentViewId();

    public void toastShort(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void toastLong(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }
}
