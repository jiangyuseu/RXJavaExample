package com.baozou.rxjavaexample.units.user.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseFragment;
import com.baozou.rxjavaexample.event.LoginEvent;
import com.baozou.rxjavaexample.model.UserBean;
import com.baozou.rxjavaexample.units.user.view.activity.LoginActivity;
import com.baozou.rxjavaexample.utils.GeneralTools;
import com.baozou.rxjavaexample.utils.UserManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

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

    @Bind(R.id.user_name)
    public TextView userName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            act = getActivity();
            rootView = inflater.inflate(R.layout.fragment_my_center, container, false);
            EventBus.getDefault().register(this);
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
        initUserInfo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView(){
        userAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!UserManager.getInstance(act).isUserLogin()){
                    Intent intent = new Intent(act, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * 初始化用户信息
     */
    public void initUserInfo() {
        UserBean user = UserManager.getInstance(act).loadUser();
        if (user != null) {
            userName.setText(user.getName());
            if (!TextUtils.isEmpty(user.getAvatar())) {
                int pixel = GeneralTools.dip2px(act, 90);
                DisplayImageOptions mAvatarImageOptions = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.user_avatar).showImageOnFail(R.mipmap.user_avatar).cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(pixel)).build();
                ImageLoader.getInstance().displayImage(user.getAvatar(), userAvatar, mAvatarImageOptions);
            }
        }
    }

    public void onEvent(LoginEvent event) {
        initUserInfo();
    }
}
