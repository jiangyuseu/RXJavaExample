package com.baozou.rxjavaexample.units.user.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.baozou.rxjavaexample.common.Constants;
import com.baozou.rxjavaexample.event.LoginEvent;
import com.baozou.rxjavaexample.model.UserBean;
import com.baozou.rxjavaexample.model.UserRequestBean;
import com.baozou.rxjavaexample.service.RegisterUserApi;
import com.baozou.rxjavaexample.units.main.view.activity.MainActivity;
import com.baozou.rxjavaexample.units.user.view.RegisterView;
import com.baozou.rxjavaexample.units.user.view.activity.RegisterSubmitActivity;
import com.baozou.rxjavaexample.utils.UserManager;

import de.greenrobot.event.EventBus;
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
public class RegisterPresenter implements IRegisterPresenter {

    private Retrofit retrofit;

    private Context mContext;

    private UserBean userBean = new UserBean();
    private RegisterView view;

    public RegisterPresenter(RegisterView view, Context context) {
        this.mContext = context;
        this.view = view;
        init();
    }

    @Override
    public void userRegist(String phone, String pwd) {
        RegisterUserApi service = retrofit.create(RegisterUserApi.class);

        //标准json形式的参数作为body请求封装
        UserRequestBean userRequestBean = new UserRequestBean();
        userRequestBean.setPhone(phone);
        userRequestBean.setPwd(pwd);

        service.submitUserInfo(userRequestBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(mContext, "complete!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mContext, "error!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(UserBean bean) {
                        userBean.setName(bean.getName());
                        userBean.setPhone(bean.getPhone());
                        userBean.setAvatar(bean.getAvatar());
                        userBean.setToken(bean.getToken());
                        userBean.setUid(bean.getUid());
                        //保存用户信息
                        if (userBean != null) {
                            UserManager.getInstance(mContext).saveUser(bean);
                            EventBus.getDefault().post(new LoginEvent());
                            Intent intent = new Intent(mContext, MainActivity.class);
                            intent.putExtra("fragment_flag",1);
                            mContext.startActivity(intent);
                            ((RegisterSubmitActivity) mContext).finish();
                        }
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
