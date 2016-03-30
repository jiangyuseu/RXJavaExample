package com.baozou.rxjavaexample.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseActivity;
import com.baozou.rxjavaexample.base.BaseFragment;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by jiangyu on 2016/3/28.
 * 圈子Fragment
 */
public class QuanziFragment extends BaseFragment {

    public static final String TAG = QuanziFragment.class.getSimpleName();
    private View rootView;
    private Activity act;

    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            act = getActivity();
            rootView = inflater.inflate(R.layout.fragment_quanzi, container, false);
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
        setupActionBar();
    }

    @OnClick(R.id.test)
    void test() {
        //打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");
                    // 提交用户信息
                    Log.i("haha", "country:" + country + ",phone:" + phone);
//                    registerUser(country, phone);
                }
            }
        });
        registerPage.show(act);
    }

    private void setupActionBar() {
        if (mToolBar != null) {
            mToolBar.setTitle("Quanzi");
            ((BaseActivity) act).setSupportActionBar(mToolBar);
            mToolBar.setTitleTextColor(Color.WHITE);
        }
    }
}
