package com.baozou.rxjavaexample.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangyu on 2016/5/11.
 * 统一的listview adapter基类
 */
public abstract class BaseListAdapter<E> extends BaseAdapter {

    private List<E> mList = new ArrayList<>();
    protected Context mContext;
    protected LayoutInflater mInflater;

    public BaseListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public BaseListAdapter(Context context, List<E> list) {
        this(context);
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public E getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void clearAll() {
        mList.clear();
    }

    public List<E> getData() {
        return mList;
    }

    public void setData(List<E> lists){
        if(lists==null||lists.size()==0){
            return ;
        }
        mList.addAll(lists);
    }

    public void add(E item){
        mList.add(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
