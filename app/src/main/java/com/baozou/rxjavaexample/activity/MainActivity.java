package com.baozou.rxjavaexample.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseActivity;
import com.baozou.rxjavaexample.common.Constants;
import com.baozou.rxjavaexample.fragment.MainFragment;
import com.baozou.rxjavaexample.fragment.MyCenterFragment;
import com.baozou.rxjavaexample.fragment.QuanziFragment;

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

    private Retrofit retrofit;

    /* 当前Tag值 */
    private String mContentTag = "";

    @Bind(R.id.icon_main)
    ImageView iconMain;

    @Bind(R.id.icon_m)
    ImageView iconM;

    @Bind(R.id.icon_e)
    ImageView iconE;

    @Bind(R.id.text_main)
    TextView textMain;

    @Bind(R.id.text_m)
    TextView textM;

    @Bind(R.id.text_e)
    TextView textE;

    @Bind(R.id.main_page)
    RelativeLayout mainPage;

    @Bind(R.id.quanzi_page)
    RelativeLayout quanziPage;

    @Bind(R.id.mycenter_page)
    RelativeLayout myCenterPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initRetrofit();
        //执行首页跳转
        if (savedInstanceState == null) {
            FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
            mContentTag = MainFragment.TAG;
            tr.replace(R.id.content, new MainFragment(), mContentTag);
            tr.commitAllowingStateLoss();
        }
    }

    private void initView() {
        iconMain.setImageResource(R.mipmap.icon_main_pre);
        textMain.setTextColor(getResources().getColor(R.color.main_text_pre));
        mainPage.getBackground().setAlpha(220);
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  // retrofit <--> rxjava
                .build();
    }

    private synchronized void switchContent(Fragment fragment, String tag) {
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.content, fragment, tag);
        mContentTag = fragment.getTag();
        tr.commitAllowingStateLoss();
    }

    @OnClick(R.id.main_page)
    void onMainPageBtnClick() {
        String mainTag = MainFragment.TAG;
        Fragment mainFragment = getSupportFragmentManager().findFragmentByTag(mainTag);
        if (mainFragment == null) {
            mainFragment = new MainFragment();
        }
        switchContent(mainFragment, mainTag);

        iconMain.setImageResource(R.mipmap.icon_main_pre);
        textMain.setTextColor(getResources().getColor(R.color.main_text_pre));
        iconM.setImageResource(R.mipmap.icon_quanzi);
        textM.setTextColor(getResources().getColor(R.color.main_text));
        iconE.setImageResource(R.mipmap.icon_my);
        textE.setTextColor(getResources().getColor(R.color.main_text));
        mainPage.getBackground().setAlpha(220);
        quanziPage.getBackground().setAlpha(255);
        myCenterPage.getBackground().setAlpha(255);
    }

    @OnClick(R.id.quanzi_page)
    void onQuanziPageBtnClick() {
        String quanziTag = QuanziFragment.TAG;
        Fragment quanziFragment = getSupportFragmentManager().findFragmentByTag(quanziTag);
        if (quanziFragment == null) {
            quanziFragment = new QuanziFragment();
        }
        switchContent(quanziFragment, quanziTag);

        iconMain.setImageResource(R.mipmap.icon_main);
        textMain.setTextColor(getResources().getColor(R.color.main_text));
        iconM.setImageResource(R.mipmap.icon_quanzi_pre);
        textM.setTextColor(getResources().getColor(R.color.main_text_pre));
        iconE.setImageResource(R.mipmap.icon_my);
        textE.setTextColor(getResources().getColor(R.color.main_text));
        mainPage.getBackground().setAlpha(255);
        quanziPage.getBackground().setAlpha(220);
        myCenterPage.getBackground().setAlpha(255);
    }

    @OnClick(R.id.mycenter_page)
    void onMyCenterPageBtnClick() {
        String myCenterTag = MyCenterFragment.TAG;
        Fragment myCenterFragment = getSupportFragmentManager().findFragmentByTag(myCenterTag);
        if (myCenterFragment == null) {
            myCenterFragment = new MyCenterFragment();
        }
        switchContent(myCenterFragment, myCenterTag);

        iconMain.setImageResource(R.mipmap.icon_main);
        textMain.setTextColor(getResources().getColor(R.color.main_text));
        iconM.setImageResource(R.mipmap.icon_quanzi);
        textM.setTextColor(getResources().getColor(R.color.main_text));
        iconE.setImageResource(R.mipmap.icon_my_pre);
        textE.setTextColor(getResources().getColor(R.color.main_text_pre));
        mainPage.getBackground().setAlpha(255);
        quanziPage.getBackground().setAlpha(255);
        myCenterPage.getBackground().setAlpha(220);
    }


//    @OnClick(R.id.btn)
//    void onBtnClick() {
//        GetGithubservice service = retrofit.create(GetGithubservice.class);
//        service.listRepos("octocat")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<List<Repo>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<Repo> repos) {
//                        btn.setText(repos.get(0).getFull_name());
//                    }
//                });
//    }
//
//    @OnClick(R.id.btn_next)
//    void onNextBtnClick() {
//        GetVideosService service = retrofit.create(GetVideosService.class);
//        service.getVideos()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<DocumentListBean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(DocumentListBean documentListBean) {
//                        Log.i("retrofit_service", "size:" + documentListBean.getData().size());
//                    }
//                });
//    }


}
