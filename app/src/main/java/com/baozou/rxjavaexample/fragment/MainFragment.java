package com.baozou.rxjavaexample.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.adapter.MainListViewAdapter;
import com.baozou.rxjavaexample.base.BaseActivity;
import com.baozou.rxjavaexample.base.BaseFragment;
import com.baozou.rxjavaexample.model.CoursesBean;
import com.baozou.rxjavaexample.service.GetMainDataApi;
import com.baozou.rxjavaexample.view.MenuProviderMain;
import com.baozou.rxjavaexample.view.topcourses.MainTopHeaderView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jiangyu on 2016/3/28.
 * 首页
 */
public class MainFragment extends BaseFragment {

    public static final String TAG = MainFragment.class.getSimpleName();
    private View rootView;
    private Activity act;

    //数据容器
    private CoursesBean coursesBean = new CoursesBean();

    //下拉刷新view
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    private MenuProviderMain menuProvider;

    @Bind(R.id.main_listview)
    ListView mListView;
    private MainListViewAdapter mAdapter;

    // 头图
    private MainTopHeaderView mHeader;

    // 百度地图定位相关
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    //请求相关
    private String location;
    private long mTimestamp;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            act = getActivity();
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
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
        initPtrViews();
        initLocation();
        setupActionBar();
        // 若不设置，onCreateOptionsMenu方法不会回调
        setHasOptionsMenu(true);
        getMainData(0);
    }

    private void initView() {
        mHeader = new MainTopHeaderView(act, coursesBean.getTop_courses());
        mAdapter = new MainListViewAdapter(act, coursesBean.getData());
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) { // 判断滚动到底部
                    if (view.getLastVisiblePosition() == (view.getCount() - 1)) {

                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        this.mListView.addHeaderView(mHeader.getHeaderView());
        mHeader.sendHanderMessage();
        this.mListView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
        mHeader.removeHandlerMessage();
    }

    private void initPtrViews() {
        swipeLayout.setColorSchemeResources(R.color.main_style_color);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    private void setupActionBar() {
        if (mToolBar != null) {
            mToolBar.setTitle("RXJava+Retrofit");
            ((BaseActivity) act).setSupportActionBar(mToolBar);
            mToolBar.setTitleTextColor(Color.WHITE);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        menuProvider = (MenuProviderMain) MenuItemCompat.getActionProvider(menu.findItem(R.id.main_menu));
        if (menuProvider != null) {
            menuProvider.setOnClickLister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.location_img) {
                        mLocationClient.start();
                        menuProvider.getTextView().setText("正在定位");
                    }
                }
            });
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initLocation() {
        mLocationClient = new LocationClient(act.getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 设置定位模式
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度，默认值gcj02
        option.setAddrType("all");
        int span = 5000;
        option.setScanSpan(span);// 设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);

    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (menuProvider != null) {
                if (TextUtils.isEmpty(location.getCity())) {
                    menuProvider.getTextView().setText("正在定位");
                } else {
                    menuProvider.getTextView().setText(location.getCity());
                    mLocationClient.stop();
                }
                Log.i("address", "" + location.getProvince() + " " + location.getCity());
            }
        }
    }

    private void getMainData(long timestamp) {
        GetMainDataApi service = retrofit.create(GetMainDataApi.class);
        service.getMainData("南京市",timestamp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<CoursesBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CoursesBean bean) {
                        coursesBean.setData(bean.getData());
                        coursesBean.setTimestamp(bean.getTimestamp());
                        coursesBean.setTop_courses(bean.getTop_courses());
                        // 刷新头图
                        mHeader.headerSetData(bean.getTop_courses());
                        mTimestamp = bean.getTimestamp();
                    }
                });
    }
}
