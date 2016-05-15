package com.baozou.rxjavaexample.units.user.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseFragment;
import com.baozou.rxjavaexample.units.user.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jiangyu on 2016/3/28.
 * 个人中心Fragment
 */
public class MyCenterFragment extends BaseFragment{
    public static final String TAG = MyCenterFragment.class.getSimpleName();
    private View rootView;
    private Activity act;

    @Bind(R.id.user_avatar)
    public ImageView userAvatar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            act = getActivity();
            rootView = inflater.inflate(R.layout.fragment_my_center, container, false);
            ButterKnife.bind(this, rootView);
        } else {
            if (rootView.getParent() != null) {
                ((ViewGroup) rootView.getParent()).removeView(rootView);
            }
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView(){
        userAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(act, LoginActivity.class);
                startActivity(intent);
            }
        });
    }



}
