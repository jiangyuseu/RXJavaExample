package com.baozou.rxjavaexample.activity;

import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRetrofit();
        setupActionBar();
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
                        Toast.makeText(MainActivity.this, "location", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }
}
