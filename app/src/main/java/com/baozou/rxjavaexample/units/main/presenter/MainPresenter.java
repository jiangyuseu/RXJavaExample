package com.baozou.rxjavaexample.units.main.presenter;

import android.content.Context;

import com.baozou.rxjavaexample.base.BasePresenter;
import com.baozou.rxjavaexample.common.ACache;
import com.baozou.rxjavaexample.common.Constants;
import com.baozou.rxjavaexample.model.CoursesBean;
import com.baozou.rxjavaexample.service.GetMainDataApi;
import com.baozou.rxjavaexample.units.main.view.MainView;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jiangyu on 2016/5/10.
 * 首页Presenter
 */
public class MainPresenter extends BasePresenter implements IMainPresenter {

    //数据容器
    private CoursesBean coursesBean = new CoursesBean();
    private ACache mCache;
    private MainView view;

    public MainPresenter(MainView view,Context context) {
        super(context);
        this.view = view;
        mCache = ACache.get(mContext);
    }

    @Override
    public void getMainData(long timestamp) {
        GetMainDataApi service = retrofit.create(GetMainDataApi.class);
        service.getMainData("南京市", timestamp)
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
                        //缓存首页数据
                        mCache.put(Constants.MAIN_CACHE_KEY, bean);
                        coursesBean.setData(bean.getData());
                        coursesBean.setTimestamp(bean.getTimestamp());
                        coursesBean.setTop_courses(bean.getTop_courses());
                        coursesBean.setItems(bean.getItems());
                        view.showMainData(coursesBean);
                        view.showHeaderData(coursesBean.getTop_courses());
                    }
                });
    }

    @Override
    public void getMainCacheData() {
        CoursesBean cacheBean = (CoursesBean) mCache.getAsObject(Constants.MAIN_CACHE_KEY);
        if(cacheBean !=null){
            view.showMainCacheData(cacheBean);
        }
    }
}
