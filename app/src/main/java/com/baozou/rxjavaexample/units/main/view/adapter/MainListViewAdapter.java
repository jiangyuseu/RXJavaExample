package com.baozou.rxjavaexample.units.main.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.JumpControlService;
import com.baozou.rxjavaexample.model.CourseBean;
import com.baozou.rxjavaexample.model.CoursesBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by jiangyu on 2016/3/28.
 * 首页列表adapter
 */
public class MainListViewAdapter extends BaseAdapter{

    private CoursesBean bean = new CoursesBean();
    private LayoutInflater mInflater;
    private DisplayImageOptions options;

    public MainListViewAdapter(Activity context) {
        this.mInflater = LayoutInflater.from(context);
        options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(40)).build();
    }

    public void setData(CoursesBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getData().size() + 1;
    }

    @Override
    public Object getItem(int i) {
        return bean.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            //倒计时或者广告入口
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        int type = getItemViewType(i);
        if (view == null) {
            switch (type) {
                case 0:
                    view = mInflater.inflate(R.layout.adapteritem_main_banner, viewGroup, false);
                    break;
                case 1:
                    holder = new ViewHolder();
                    view = mInflater.inflate(R.layout.adapteritem_main_listview, viewGroup, false);
                    holder.itemImg = (ImageView) view.findViewById(R.id.main_item_image);
                    holder.userImg = (ImageView) view.findViewById(R.id.main_item_user_img);
                    holder.userName = (TextView) view.findViewById(R.id.main_item_user_name);
                    holder.itemTitle = (TextView) view.findViewById(R.id.main_item_title);
                    holder.itemCourse = (TextView) view.findViewById(R.id.main_item_tag);
                    holder.itemNumber = (TextView) view.findViewById(R.id.main_item_number);
                    view.setTag(holder);
                    break;
            }
        } else {
            switch (type) {
                case 0:
                    holder = (ViewHolder) view.getTag();
                    break;
            }
        }


        if (type == 1) {
            CourseBean mBean = null;
            if (i > 1) {
                mBean = bean.getData().get(i - 2);
            }
            try {
                ImageLoader.getInstance().displayImage(mBean.getImage(), holder.itemImg);
                ImageLoader.getInstance().displayImage(mBean.getTeacher().getAvatar(), holder.userImg, options);
                holder.userName.setText(mBean.getTeacher().getName());
                holder.itemTitle.setText(mBean.getTitle());
                holder.itemCourse.setText(mBean.getName());
                holder.itemNumber.setText("|" + mBean.getTotal() + "人");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return view;
    }

    private class ViewHolder {
        private ImageView itemImg;
        private ImageView userImg;
        private TextView userName;
        private TextView itemTitle;
        private TextView itemCourse;
        private TextView itemNumber;
    }
}
