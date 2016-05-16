package com.baozou.rxjavaexample.service;

import com.baozou.rxjavaexample.model.UserBean;
import com.baozou.rxjavaexample.model.UserRequestBean;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by lenovo on 2016/3/29.
 * 登录api
 */
public interface LoginApi {
    @POST("teaching/user/base/_login")
    Observable<UserBean> login(@Body UserRequestBean user);
}

