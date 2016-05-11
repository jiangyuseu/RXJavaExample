package com.baozou.rxjavaexample.units.main.presenter;

/**
 * Created by jiangyu on 2016/5/10.
 * 首页数据接口
 */
public interface IMainPresenter{
    /**
     * 获取首页数据
     */
    void getMainData(long timestamp);

    /**
     * 获取缓存数据
     */
    void getMainCacheData();
}
