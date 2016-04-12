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
import com.baozou.rxjavaexample.model.CoursesBean;
import com.baozou.rxjavaexample.view.HorizontalListView;
import com.baozou.rxjavaexample.view.PinnedSectionListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by jiangyu on 2016/3/28.
 * 首页列表adapter
 */
public class MainListViewAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter {

    private Context mContext;
    private CoursesBean bean;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;
    private View cateView;
    private MainCategoryAdapter categoryAdapter;

    public MainListViewAdapter(Context context, CoursesBean bean) {
        this.mContext = context;
        this.bean = bean;
        this.mInflater = LayoutInflater.from(context);
        options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(40)).build();
        categoryAdapter = new MainCategoryAdapter(context,bean.getItems());
    }

    public void setData(CoursesBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        //pos =0 为类目
        return bean.getData().size()+1;
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
    public boolean isItemViewTypePinned(int viewType) {
        if (viewType == 0) {
            return true;
        }
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        CategoryHolder cateHolder = null;
        int type = getItemViewType(i);
        if (view == null) {
            switch (type) {
                case 0:
                    cateHolder = new CategoryHolder();
                    view = mInflater.inflate(R.layout.adapteritem_main_category, viewGroup, false);
                    cateHolder.category = (HorizontalListView)view.findViewById(R.id.category_list);
                    view.setTag(cateHolder);
                    break;
                case 1:
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
                    break;
            }
        } else {
            switch (type){
                case 0:
                    cateHolder = (CategoryHolder)view.getTag();
                    break;
                case 1:
                    holder = (ViewHolder) view.getTag();
                    break;
            }
        }


        if(type == 0){
            if(bean.getItems()!=null && bean.getItems().size()>0){
                cateHolder.category.setAdapter(categoryAdapter);
            }
        }else if(type == 1){
            CourseBean mBean = null;
            if(i > 0){
                mBean = bean.getData().get(i-1);
            }
            ImageLoader.getInstance().displayImage(mBean.getImage(), holder.itemImg);
            ImageLoader.getInstance().displayImage(mBean.getTeacher().getAvatar(), holder.userImg, options);
            holder.userName.setText(mBean.getTeacher().getName());
            holder.itemTitle.setText(mBean.getTitle());
            holder.itemCourse.setText(mBean.getName());
            holder.itemNumber.setText("|" + mBean.getTotal() + "人");
        }

        return view;
    }

    private class CategoryHolder{
        private HorizontalListView category;
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
