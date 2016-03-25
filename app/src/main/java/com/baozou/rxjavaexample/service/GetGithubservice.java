package com.baozou.rxjavaexample.service;

import com.baozou.rxjavaexample.model.Repo;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by jiangyu on 2016/3/24.
 * retrofit 方式 api
 */
public interface GetGithubservice {
    @GET("users/{user}/repos")
    Observable<List<Repo>> listRepos(@Path("user") String user);
}
