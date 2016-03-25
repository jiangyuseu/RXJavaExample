package com.baozou.rxjavaexample.service;

import com.baozou.rxjavaexample.model.DocumentListBean;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by jiangyu on 2016/3/25.
 * retrofit 方式获取首页视频数据
 */
public interface GetVideosService {
    @GET("api/v30/documents/videos/latest")
    Observable<DocumentListBean> getVideos();
}
