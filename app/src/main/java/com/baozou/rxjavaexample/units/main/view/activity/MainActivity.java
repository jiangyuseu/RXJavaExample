package com.baozou.rxjavaexample.units.main.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseActivity;
import com.baozou.rxjavaexample.fragment.InfoFragment;
import com.baozou.rxjavaexample.fragment.VideoFragment;
import com.baozou.rxjavaexample.units.main.view.fragment.MainFragment;
import com.baozou.rxjavaexample.units.user.view.fragment.MyCenterFragment;
import com.baozou.rxjavaexample.view.TabFooterView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jiangyu on 2016/3/28.
 * 首页
 */
public class MainActivity extends BaseActivity implements TabFooterView.TabClickListener {

    /* 当前Tag值 */
    private String mContentTag = "";

    @Bind(R.id.tab_foot)
    public TabFooterView footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        footer.initTab();
        footer.setTabClickListener(this);

        //执行首页跳转
        if (savedInstanceState == null) {
            FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
            mContentTag = MainFragment.TAG;
            tr.replace(R.id.content, new MainFragment(), mContentTag);
            tr.commitAllowingStateLoss();
        }

    }

    @Override
    public void MainTab() {
        String mainTag = MainFragment.TAG;
        Fragment mainFragment1 = getSupportFragmentManager().findFragmentByTag(mainTag);
        if (mainFragment1 == null) {
            mainFragment1 = new MainFragment();
        }
        switchContent(mainFragment1, mainTag);
    }

    @Override
    public void VideoTab() {
        String videoTag = VideoFragment.TAG;
        Fragment videoFragment = getSupportFragmentManager().findFragmentByTag(videoTag);
        if (videoFragment == null) {
            videoFragment = new VideoFragment();
        }
        switchContent(videoFragment, videoTag);
    }

    @Override
    public void InfoTab() {
        String infoTag = InfoFragment.TAG;
        Fragment infoFragment = getSupportFragmentManager().findFragmentByTag(infoTag);
        if (infoFragment == null) {
            infoFragment = new InfoFragment();
        }
        switchContent(infoFragment, infoTag);
    }

    @Override
    public void MyTab() {
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
