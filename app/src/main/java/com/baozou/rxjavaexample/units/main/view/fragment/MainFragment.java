package com.baozou.rxjavaexample.units.main.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseFragment;
import com.baozou.rxjavaexample.common.ACache;
import com.baozou.rxjavaexample.common.Constants;
import com.baozou.rxjavaexample.model.CourseBean;
import com.baozou.rxjavaexample.model.CoursesBean;
import com.baozou.rxjavaexample.units.main.presenter.IMainPresenter;
import com.baozou.rxjavaexample.units.main.presenter.MainPresenter;
import com.baozou.rxjavaexample.units.main.view.MainView;
import com.baozou.rxjavaexample.units.main.view.adapter.MainListViewAdapter;
import com.baozou.rxjavaexample.view.MenuProviderMain;
import com.baozou.rxjavaexample.view.topcourses.MainTopHeaderView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jiangyu on 2016/3/28.
 * 首页
 */
public class MainFragment extends BaseFragment implements MainView {

    public static final String TAG = MainFragment.class.getSimpleName();
    private View rootView;
    private Activity act;

    //下拉刷新view
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeLayout;

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

    private IMainPresenter mPresenter;


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
        mPresenter = new MainPresenter(this,act);
        initView();
        initPtrViews();
        initLocation();
        initData();
    }

    @Override
    public void showMainData(CoursesBean bean) {
        //刷新列表数据
        mAdapter.setData(bean);
        mAdapter.notifyDataSetChanged();
        mTimestamp = bean.getTimestamp();
    }

    @Override
    public void showHeaderData(List<CourseBean> list) {
        // 刷新头图
        mHeader.headerSetData(list);
    }

    @Override
    public void showMainCacheData(CoursesBean bean) {
        mAdapter.setData(bean);
        mAdapter.notifyDataSetChanged();
        mHeader.headerSetData(bean.getTop_courses());
    }

    private void initData(){
        mPresenter.getMainCacheData();
        mPresenter.getMainData(0);
    }

    private void initView() {
        mHeader = new MainTopHeaderView(act);
        mAdapter = new MainListViewAdapter(act);
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
}
