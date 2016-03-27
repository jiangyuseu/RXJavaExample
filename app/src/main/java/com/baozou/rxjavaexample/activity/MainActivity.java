package com.baozou.rxjavaexample.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.common.Constants;
import com.baozou.rxjavaexample.model.DocumentListBean;
import com.baozou.rxjavaexample.model.Repo;
import com.baozou.rxjavaexample.service.GetGithubservice;
import com.baozou.rxjavaexample.service.GetVideosService;
import com.baozou.rxjavaexample.view.MenuProviderMain;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn)
    TextView btn;

    private Retrofit retrofit;

    @Bind(R.id.toolbar)
    Toolbar mToolBar;

    private MenuProviderMain menuProvider;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initLocation();
        initRetrofit();
        setupActionBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mLocationClient.isStarted()){
            mLocationClient.stop();
        }
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  // retrofit <--> rxjava
                .build();
    }

    @OnClick(R.id.btn)
    void onBtnClick() {
        GetGithubservice service = retrofit.create(GetGithubservice.class);
        service.listRepos("octocat")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        btn.setText(repos.get(0).getFull_name());
                    }
                });
    }

    @OnClick(R.id.btn_next)
    void onNextBtnClick() {
        GetVideosService service = retrofit.create(GetVideosService.class);
        service.getVideos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<DocumentListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DocumentListBean documentListBean) {
                        Log.i("retrofit_service", "size:" + documentListBean.getData().size());
                    }
                });
    }

    private void setupActionBar() {
        if (mToolBar != null) {
            mToolBar.setTitle("RXJava+Retrofit");
            setSupportActionBar(mToolBar);
            mToolBar.setTitleTextColor(Color.WHITE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menuProvider = (MenuProviderMain) MenuItemCompat.getActionProvider(menu.findItem(R.id.main_menu));
        if (menuProvider != null) {
            menuProvider.setOnClickLister(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.location_img) {
                        mLocationClient.start();
                    }
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
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
                if(location==null){
                    menuProvider.getTextView().setText("正在定位");
                }else{
                    menuProvider.getTextView().setText(location.getCity());
                    mLocationClient.stop();
                }
                Log.i("address", "" + location.getProvince() + " " + location.getCity());
            }
        }
    }
}
