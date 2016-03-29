package com.baozou.rxjavaexample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.baozou.rxjavaexample.model.CoursesBean;

/**
 * Created by jiangyu on 2016/3/28.
 * 首页列表adapter
 */
public class MainListViewAdapter extends BaseAdapter{

    private Context mContext;
    private CoursesBean bean;

    public MainListViewAdapter(Context context){
        this.mContext = context;
    }

    private void setData(CoursesBean bean){
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getData().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
