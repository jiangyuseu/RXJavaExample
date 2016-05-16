package com.baozou.rxjavaexample.service;

import com.baozou.rxjavaexample.model.UserBean;
import com.baozou.rxjavaexample.model.UserRequestBean;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by lenovo on 2016/3/29.
 * 用户注册api
 */
public interface RegisterUserApi {
    @POST("teaching/user/base/_reg")
    Observable<UserBean> submitUserInfo(@Body UserRequestBean user);
}

