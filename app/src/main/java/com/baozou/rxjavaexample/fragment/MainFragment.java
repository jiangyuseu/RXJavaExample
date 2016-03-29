package com.baozou.rxjavaexample.fragment;

import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.adapter.MainListViewAdapter;
import com.baozou.rxjavaexample.base.BaseActivity;
import com.baozou.rxjavaexample.base.BaseFragment;
import com.baozou.rxjavaexample.model.ADInfo;
import com.baozou.rxjavaexample.model.CourseBean;
import com.baozou.rxjavaexample.model.CoursesBean;
import com.baozou.rxjavaexample.service.GetMainDataApi;
import com.baozou.rxjavaexample.view.MenuProviderMain;
import com.baozou.rxjavaexample.view.adview.CycleViewPager;
import com.baozou.rxjavaexample.view.adview.ViewFactory;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

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

    //首页轮播图相关
    private List<ImageView> views = new ArrayList<>();
    private List<ADInfo> infos = new ArrayList<>();
    private CycleViewPager cycleViewPager;
    private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};

    //数据容器
    private CoursesBean coursesBean = new CoursesBean();

    //下拉刷新view
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    @Bind(R.id.main_listview)
    ListView mListView;

    private MainListViewAdapter mAdapter;

    private MenuProviderMain menuProvider;

    //百度地图定位相关
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
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
        configImageLoader();
        initView();
        initPtrViews();
        initLocation();
        setupActionBar();
        // 若不设置，onCreateOptionsMenu方法不会回调
        setHasOptionsMenu(true);
        getMainData();
    }

    private void initView(){
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
        this.mListView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        FragmentManager fm = getActivity().getFragmentManager();
        Fragment fragment = (fm.findFragmentById(R.id.ad_view_fragment));
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
    }

    /**
     * 配置ImageLoder
     */
    private void configImageLoader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                        // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity().getApplicationContext()).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    @SuppressLint("NewApi")
    private void initAds() {

        cycleViewPager = (CycleViewPager) getActivity().getFragmentManager()
                .findFragmentById(R.id.ad_view_fragment);

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(act, coursesBean.getTop_courses().get(coursesBean.getTop_courses().size() - 1).getImage()));

        for (int i = 0; i < coursesBean.getTop_courses().size(); i++) {
            views.add(ViewFactory.getImageView(act, coursesBean.getTop_courses().get(i).getImage()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(act, coursesBean.getTop_courses().get(0).getImage()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, coursesBean.getTop_courses(), mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(CourseBean info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
                Toast.makeText(act,
                        "position-->" + info.getDescription(), Toast.LENGTH_SHORT)
                        .show();
            }

        }
    };

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
        mLocationClient = new LocationClient(act.getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
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

    private void getMainData(){
        GetMainDataApi service = retrofit.create(GetMainDataApi.class);
        service.getMainData()
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
                        initAds();
                        Log.i("coursesbean",coursesBean.getData().get(0).getDescription());
                    }
                });
    }
}
