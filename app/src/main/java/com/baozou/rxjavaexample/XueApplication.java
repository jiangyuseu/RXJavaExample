package com.baozou.rxjavaexample;

import android.app.Application;
import android.content.Context;

import com.baozou.rxjavaexample.common.Constants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import cn.smssdk.SMSSDK;

/**
 * Created by jiangyu on 2016/3/30.
 */
public class XueApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }

    public static void initImageLoader(Context pContext) {
        DisplayImageOptions defaultDisOpt = new DisplayImageOptions.Builder().
                cacheInMemory(true).
                cacheOnDisc(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(pContext).
                denyCacheImageMultipleSizesInMemory().
                tasksProcessingOrder(QueueProcessingType.LIFO).
                discCacheFileCount(2000).
                defaultDisplayImageOptions(defaultDisOpt).build();
        ImageLoader.getInstance().init(config);
    }

}
