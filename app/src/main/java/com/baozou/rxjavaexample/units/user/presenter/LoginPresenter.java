package com.baozou.rxjavaexample.units.user.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.baozou.rxjavaexample.base.BasePresenter;
import com.baozou.rxjavaexample.common.Constants;
import com.baozou.rxjavaexample.event.LoginEvent;
import com.baozou.rxjavaexample.model.UserBean;
import com.baozou.rxjavaexample.model.UserRequestBean;
import com.baozou.rxjavaexample.service.LoginApi;
import com.baozou.rxjavaexample.units.main.view.activity.MainActivity;
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
 * Created by jiangyu on 2016/5/16.
 */
public class LoginPresenter extends BasePresenter implements ILoginPresenter{

    private UserBean userBean = new UserBean();

    public LoginPresenter(Context context) {
        super(context);
    }

    @Override
    public void userLogin(String phone, String pwd) {
        LoginApi service = retrofit.create(LoginApi.class);

        //标准json形式的参数作为body请求封装
        UserRequestBean userRequestBean = new UserRequestBean();
        userRequestBean.setPhone(phone);
        userRequestBean.setPwd(pwd);

        service.login(userRequestBean)
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
}
