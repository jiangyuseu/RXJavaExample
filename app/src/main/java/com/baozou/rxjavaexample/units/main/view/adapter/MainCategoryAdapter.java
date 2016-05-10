package com.baozou.rxjavaexample.units.main.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.model.ItemBean;
import com.baozou.rxjavaexample.view.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/4/12.
 * 首页类目的adapter
 */
public class MainCategoryAdapter extends BaseAdapter{

    private List<ItemBean> itemBeanList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public MainCategoryAdapter(Context context,List<ItemBean> items){
        this.context = context;
        this.itemBeanList = items;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CategoryItemHolder holder = null;
        if (view == null) {
            holder = new CategoryItemHolder();
            view = mInflater.inflate(R.layout.adapteritem_main_category_item, viewGroup, false);
            holder.categoryTxt = (TextView)view.findViewById(R.id.category_txt);
            view.setTag(holder);
        }else{
            holder = (CategoryItemHolder) view.getTag();
        }
        ItemBean item = itemBeanList.get(i);
        holder.categoryTxt.setText(item.getName());
        return null;
    }

    private class CategoryItemHolder{
        private TextView categoryTxt;
    }


}

