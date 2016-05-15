package com.baozou.rxjavaexample.units.user.presenter;

import android.content.Context;
import com.baozou.rxjavaexample.common.Constants;
import com.baozou.rxjavaexample.model.UserBean;
import com.baozou.rxjavaexample.service.RegisterUserApi;
import com.baozou.rxjavaexample.units.user.view.RegisterView;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2016/5/15.
 * 用户注册presenter
 */
public class RegisterPresenter implements IRegisterPresenter{

    private Retrofit retrofit;

    private Context mContext;

    private UserBean userBean = new UserBean();
    private RegisterView view;

    public RegisterPresenter(RegisterView view,Context context) {
        this.mContext = context;
        this.view = view;
        init();
    }

    @Override
    public void userRegist(String phone, String pwd) {
        RegisterUserApi service = retrofit.create(RegisterUserApi.class);
        service.submitUserInfo(phone, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserBean bean) {
                        userBean.setUsername(bean.getUsername());
                        userBean.setAvatar(bean.getAvatar());
                        userBean.setToken(bean.getToken());
                        view.showUserInfo(userBean);
                    }
                });
    }

    private void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  // retrofit <--> rxjava
                .build();
    }

}
