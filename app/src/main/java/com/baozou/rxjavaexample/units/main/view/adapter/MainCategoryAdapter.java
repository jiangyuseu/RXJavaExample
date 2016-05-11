package com.baozou.rxjavaexample.units.main.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseListAdapter;
import com.baozou.rxjavaexample.base.JumpControlService;
import com.baozou.rxjavaexample.model.ItemBean;
import com.baozou.rxjavaexample.view.HorizontalListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/4/12.
 * 首页类目的adapter
 */
public class MainCategoryAdapter extends BaseListAdapter<ItemBean> {

    public MainCategoryAdapter(Context context, List<ItemBean> items) {
        super(context, items);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CategoryItemHolder holder = null;
        if (view == null) {
            holder = new CategoryItemHolder();
            view = mInflater.inflate(R.layout.item_mainitem, viewGroup, false);
            holder.itemTxt = (TextView) view.findViewById(R.id.item_text);
            holder.itemImg = (ImageView)view.findViewById(R.id.item_icon);
            holder.itemLayout = (RelativeLayout)view.findViewById(R.id.item_layout);
            view.setTag(holder);
        } else {
            holder = (CategoryItemHolder) view.getTag();
        }
        final ItemBean item = getItem(i);
        holder.itemTxt.setText(item.getName());
        ImageLoader.getInstance().displayImage(item.getIcon(), holder.itemImg);
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, JumpControlService.class);
                intent.putExtra("url", item.getUrl());
                mContext.startService(intent);
            }
        });
        return view;
    }

    private class CategoryItemHolder {
        private TextView itemTxt;
        private ImageView itemImg;
        private RelativeLayout itemLayout;
    }

}

