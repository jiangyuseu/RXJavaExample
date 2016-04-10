package com.baozou.rxjavaexample.utils;

/**
 * Created by lenovo on 2016/4/10.
 */
public class BuildConfig {

    /***
     * TODO 发布软件时该模式需要修改为 false
     * 之所以不使用系统提供的DEBUG开关
     * 1：系统提供的开关与包名挂钩 主包名修改后导入的开关需要修改
     * 2：导出包后在测试阶段仍然需要输出调试信息
     */
    public final static boolean DEVELOP_MODE = true;

    /***
     * TODO 发布软件时该模式需要修改为 false
     * 网络框架DEBUG开关
     */
    public static boolean NETWORK_DEBUG = true;

    /*
     * TODO 发布软件时该模式需要修改为 false
     * API接口DEBUG开关
     */
    public static final boolean API_DEBUG = false;

    /*
     * TODO 发布软件时该模式需要修改为 false
     * 是否启用弱网络测试环境
     */
    public final static boolean NETWORK_WEAK_MODE = false;


}