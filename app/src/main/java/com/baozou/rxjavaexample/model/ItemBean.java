package com.baozou.rxjavaexample.model;

/**
 * Created by jiangyu on 2016/4/12.
 * 首页具体类目item
 */
public class ItemBean {
    private long iid;
    private String name;
    private String icon;
    private String url;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getIid() {
        return iid;
    }

    public void setIid(long iid) {
        this.iid = iid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
