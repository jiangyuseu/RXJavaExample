package com.baozou.rxjavaexample.units.video.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseFragment;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by jiangyu on 2016/3/28.
 * Video Fragment
 */
public class VideoFragment extends BaseFragment {

    public static final String TAG = VideoFragment.class.getSimpleName();
    private View rootView;
    private Activity act;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            act = getActivity();
            rootView = inflater.inflate(R.layout.fragment_video, container, false);
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
    }



}
