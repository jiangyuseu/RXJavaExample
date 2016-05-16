package com.baozou.rxjavaexample.base;

import android.content.Context;
import com.baozou.rxjavaexample.common.Constants;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by jiangyu on 2016/5/16.
 */
public class BasePresenter {

    public Retrofit retrofit;
    public Context mContext;

    public BasePresenter(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  // retrofit <--> rxjava
                .build();
    }


}
