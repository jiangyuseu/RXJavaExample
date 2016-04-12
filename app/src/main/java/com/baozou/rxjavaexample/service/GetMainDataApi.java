package com.baozou.rxjavaexample.service;

import com.baozou.rxjavaexample.model.CoursesBean;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by lenovo on 2016/3/29.
 * 获取首页数据api
 */
public interface GetMainDataApi {
    @GET("teaching/course/_get")
    Observable<CoursesBean> getMainData(@Query("location") String location,@Query("timestamp") long timestamp);
}

