package com.baozou.rxjavaexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.model.CourseBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by jiangyu on 2016/3/28.
 * 首页列表adapter
 */
public class MainListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<CourseBean> bean;
    private LayoutInflater mInflater;

    public MainListViewAdapter(Context context, List<CourseBean> bean) {
        this.mContext = context;
        this.bean = bean;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bean.size();
    }

    @Override
    public Object getItem(int i) {
        return bean.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.adapteritem_main_listview, viewGroup, false);
            holder.contentView = (RelativeLayout) view.findViewById(R.id.content_view);
            holder.itemImg = (ImageView) view.findViewById(R.id.main_item_image);
            holder.userImg = (ImageView) view.findViewById(R.id.main_item_user_img);
            holder.userName = (TextView) view.findViewById(R.id.main_item_user_name);
            holder.itemTitle = (TextView) view.findViewById(R.id.main_item_title);
            holder.itemCourse = (TextView) view.findViewById(R.id.main_item_tag);
            holder.itemNumber = (TextView) view.findViewById(R.id.main_item_number);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        CourseBean mBean = bean.get(i);
        ImageLoader.getInstance().displayImage(mBean.getImage(),holder.itemImg);
        ImageLoader.getInstance().displayImage(mBean.getTeacher().getAvatar(),holder.userImg);
        holder.userName.setText(mBean.getTeacher().getName());
        holder.itemTitle.setText(mBean.getTitle());
        holder.itemCourse.setText(mBean.getName());
        holder.itemNumber.setText(mBean.getTotal());

        return view;
    }

    private class ViewHolder {
        private RelativeLayout contentView;
        private ImageView itemImg;
        private ImageView userImg;
        private TextView userName;
        private TextView itemTitle;
        private TextView itemCourse;
        private TextView itemNumber;
    }
}
