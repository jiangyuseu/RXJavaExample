package com.baozou.rxjavaexample.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseActivity;
import com.baozou.rxjavaexample.common.Constants;
import com.baozou.rxjavaexample.fragment.MainFragment;
import com.baozou.rxjavaexample.fragment.MyCenterFragment;
import com.baozou.rxjavaexample.fragment.QuanziFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by jiangyu on 2016/3/28.
 * 首页
 */
public class MainActivity extends BaseActivity {

    /* 当前Tag值 */
    private String mContentTag = "";

//    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // Bottmobar init
//        bottomBar = BottomBar.attach(this, savedInstanceState);
//        bottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
//            @Override
//            public void onMenuTabSelected(@IdRes int menuItemId) {
//                jumpFragment(menuItemId);
//            }
//
//            @Override
//            public void onMenuTabReSelected(@IdRes int menuItemId) {
//
//            }
//        });
//        bottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.main_style_color));

        //执行首页跳转
        if (savedInstanceState == null) {
            FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
            mContentTag = MainFragment.TAG;
            tr.replace(R.id.content, new MainFragment(), mContentTag);
            tr.commitAllowingStateLoss();
        }
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
//        bottomBar.onSaveInstanceState(outState);
    }

    private void jumpFragment(int menuItemId) {
        switch (menuItemId) {
            case R.id.bb_menu_main:
                String mainTag = MainFragment.TAG;
                Fragment mainFragment = getSupportFragmentManager().findFragmentByTag(mainTag);
                if (mainFragment == null) {
                    mainFragment = new MainFragment();
                }
                switchContent(mainFragment, mainTag);
                break;
            case R.id.bb_menu_video:
                String myCenterTag1 = MyCenterFragment.TAG;
                Fragment myCenterFragment1 = getSupportFragmentManager().findFragmentByTag(myCenterTag1);
                if (myCenterFragment1 == null) {
                    myCenterFragment1 = new MyCenterFragment();
                }
                switchContent(myCenterFragment1, myCenterTag1);
                break;
            case R.id.bb_menu_quanzi:
                String quanziTag = QuanziFragment.TAG;
                Fragment quanziFragment = getSupportFragmentManager().findFragmentByTag(quanziTag);
                if (quanziFragment == null) {
                    quanziFragment = new QuanziFragment();
                }
                switchContent(quanziFragment, quanziTag);
                break;
            case R.id.bb_menu_my:
                String myCenterTag = MyCenterFragment.TAG;
                Fragment myCenterFragment = getSupportFragmentManager().findFragmentByTag(myCenterTag);
                if (myCenterFragment == null) {
                    myCenterFragment = new MyCenterFragment();
                }
                switchContent(myCenterFragment, myCenterTag);
                break;
        }
    }
}
