package com.baozou.rxjavaexample.units.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseActivity;
import com.baozou.rxjavaexample.fragment.InfoFragment;
import com.baozou.rxjavaexample.fragment.MainFragment;
import com.baozou.rxjavaexample.fragment.MyCenterFragment;
import com.baozou.rxjavaexample.fragment.VideoFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jiangyu on 2016/3/28.
 * 首页
 */
public class MainActivity extends BaseActivity {

    /* 当前Tag值 */
    private String mContentTag = "";

    @Bind(R.id.home_layout)
    public RelativeLayout mainLayout;
    @Bind(R.id.video_layout)
    public RelativeLayout videoLayout;
    @Bind(R.id.info_layout)
    public RelativeLayout infoLayout;
    @Bind(R.id.my_layout)
    public RelativeLayout myLayout;

    @Bind(R.id.home_icon)
    public ImageView mainImg;
    @Bind(R.id.video_icon)
    public ImageView videoImg;
    @Bind(R.id.info_icon)
    public ImageView infoImg;
    @Bind(R.id.my_icon)
    public ImageView myImg;
    @Bind(R.id.home_txt)
    public TextView mainTxt;
    @Bind(R.id.video_txt)
    public TextView videoTxt;
    @Bind(R.id.info_txt)
    public TextView infoTxt;
    @Bind(R.id.my_txt)
    public TextView myTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView(savedInstanceState);

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHomeClick();
            }
        });

        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onVideoClick();
            }
        });

        infoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInfoClick();
            }
        });

        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMyClick();
            }
        });
    }

    private void initView(Bundle savedInstanceState) {
        mainImg.setImageResource(R.mipmap.ic_home_pressed_n);
        mainTxt.setTextColor(getResources().getColor(R.color.main_style_color));
        //执行首页跳转
        if (savedInstanceState == null) {
            FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
            mContentTag = MainFragment.TAG;
            tr.replace(R.id.content, new MainFragment(), mContentTag);
            tr.commitAllowingStateLoss();
        }
    }

    private void onHomeClick() {
        mainImg.setImageResource(R.mipmap.ic_home_pressed_n);
        mainTxt.setTextColor(getResources().getColor(R.color.main_style_color));
        String mainTag = MainFragment.TAG;
        Fragment mainFragment = getSupportFragmentManager().findFragmentByTag(mainTag);
        if (mainFragment == null) {
            mainFragment = new MainFragment();
        }
        switchContent(mainFragment, mainTag);
    }

    private void onVideoClick() {
        mainImg.setImageResource(R.mipmap.ic_home_normal_n);
        mainTxt.setTextColor(getResources().getColor(R.color.main_btn_no_press));
        String videoTag = VideoFragment.TAG;
        Fragment videoFragment = getSupportFragmentManager().findFragmentByTag(videoTag);
        if (videoFragment == null) {
            videoFragment = new VideoFragment();
        }
        switchContent(videoFragment, videoTag);
    }

    private void onInfoClick() {
        mainImg.setImageResource(R.mipmap.ic_home_normal_n);
        mainTxt.setTextColor(getResources().getColor(R.color.main_btn_no_press));
        String infoTag = InfoFragment.TAG;
        Fragment infoFragment = getSupportFragmentManager().findFragmentByTag(infoTag);
        if (infoFragment == null) {
            infoFragment = new InfoFragment();
        }
        switchContent(infoFragment, infoTag);
    }

    private void onMyClick() {
        mainImg.setImageResource(R.mipmap.ic_home_normal_n);
        mainTxt.setTextColor(getResources().getColor(R.color.main_btn_no_press));
        String myTag = MyCenterFragment.TAG;
        Fragment myFragment = getSupportFragmentManager().findFragmentByTag(myTag);
        if (myFragment == null) {
            myFragment = new MyCenterFragment();
        }
        switchContent(myFragment, myTag);
    }

    private synchronized void switchContent(Fragment fragment, String tag) {
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.content, fragment, tag);
        mContentTag = fragment.getTag();
        tr.commitAllowingStateLoss();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

}
