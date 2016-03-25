package com.baozou.rxjavaexample.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangyu on 2016/3/12.
 * 新的文章Bean
 */
public class DocumentListBean{

    private long timestamp;

    private List<DocumentBean> data = new ArrayList<>();

    public List<DocumentBean> getData() {
        return data;
    }

    public void setData(List<DocumentBean> data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
