package com.baozou.rxjavaexample.units.main;

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
import com.baozou.rxjavaexample.view.PinnedSectionListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by jiangyu on 2016/3/28.
 * 首页列表adapter
 */
public class MainListViewAdapter extends BaseAdapter implements View.OnClickListener {

    private Activity mContext;
    private CoursesBean bean;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;
    private View cateView;
    private MainCategoryAdapter categoryAdapter;

    public MainListViewAdapter(Activity context, CoursesBean bean) {
        this.mContext = context;
        this.bean = bean;
        this.mInflater = LayoutInflater.from(context);
        options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(40)).build();
        categoryAdapter = new MainCategoryAdapter(context, bean.getItems());
    }

    public void setData(CoursesBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        //pos =0 为类目
        return bean.getData().size() + 2;
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
        } else if (position == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
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
                    cateHolder.icon1 = (ImageView) view.findViewById(R.id.item_icon_1);
                    cateHolder.icon2 = (ImageView) view.findViewById(R.id.item_icon_2);
                    cateHolder.icon3 = (ImageView) view.findViewById(R.id.item_icon_3);
                    cateHolder.icon4 = (ImageView) view.findViewById(R.id.item_icon_4);
                    cateHolder.icon5 = (ImageView) view.findViewById(R.id.item_icon_5);
                    cateHolder.icon6 = (ImageView) view.findViewById(R.id.item_icon_6);
                    cateHolder.icon7 = (ImageView) view.findViewById(R.id.item_icon_7);
                    cateHolder.icon8 = (ImageView) view.findViewById(R.id.item_icon_8);

                    cateHolder.text1 = (TextView) view.findViewById(R.id.item_text_1);
                    cateHolder.text2 = (TextView) view.findViewById(R.id.item_text_2);
                    cateHolder.text3 = (TextView) view.findViewById(R.id.item_text_3);
                    cateHolder.text4 = (TextView) view.findViewById(R.id.item_text_4);
                    cateHolder.text5 = (TextView) view.findViewById(R.id.item_text_5);
                    cateHolder.text6 = (TextView) view.findViewById(R.id.item_text_6);
                    cateHolder.text7 = (TextView) view.findViewById(R.id.item_text_7);
                    cateHolder.text8 = (TextView) view.findViewById(R.id.item_text_8);

                    cateHolder.layout1 = (RelativeLayout) view.findViewById(R.id.layout1);
                    cateHolder.layout2 = (RelativeLayout) view.findViewById(R.id.layout2);
                    cateHolder.layout3 = (RelativeLayout) view.findViewById(R.id.layout3);
                    cateHolder.layout4 = (RelativeLayout) view.findViewById(R.id.layout4);
                    cateHolder.layout5 = (RelativeLayout) view.findViewById(R.id.layout5);
                    cateHolder.layout6 = (RelativeLayout) view.findViewById(R.id.layout6);
                    cateHolder.layout7 = (RelativeLayout) view.findViewById(R.id.layout7);
                    cateHolder.layout8 = (RelativeLayout) view.findViewById(R.id.layout8);

                    view.setTag(cateHolder);

                    break;
                case 1:
                    view = mInflater.inflate(R.layout.adapteritem_main_banner, viewGroup, false);
                    break;
                case 2:
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
            switch (type) {
                case 0:
                    cateHolder = (CategoryHolder) view.getTag();
                    break;
                case 1:
                    holder = (ViewHolder) view.getTag();
                    break;
            }
        }


        if (type == 0) {
            if (bean != null && bean.getItems().size() == 8) {
                ImageLoader.getInstance().displayImage(bean.getItems().get(0).getIcon(), cateHolder.icon1);
                cateHolder.text1.setText(bean.getItems().get(0).getName());

                ImageLoader.getInstance().displayImage(bean.getItems().get(1).getIcon(), cateHolder.icon2);
                cateHolder.text2.setText(bean.getItems().get(1).getName());

                ImageLoader.getInstance().displayImage(bean.getItems().get(2).getIcon(), cateHolder.icon3);
                cateHolder.text3.setText(bean.getItems().get(2).getName());

                ImageLoader.getInstance().displayImage(bean.getItems().get(3).getIcon(), cateHolder.icon4);
                cateHolder.text4.setText(bean.getItems().get(3).getName());

                ImageLoader.getInstance().displayImage(bean.getItems().get(4).getIcon(), cateHolder.icon5);
                cateHolder.text5.setText(bean.getItems().get(4).getName());

                ImageLoader.getInstance().displayImage(bean.getItems().get(5).getIcon(), cateHolder.icon6);
                cateHolder.text6.setText(bean.getItems().get(5).getName());

                ImageLoader.getInstance().displayImage(bean.getItems().get(6).getIcon(), cateHolder.icon7);
                cateHolder.text7.setText(bean.getItems().get(6).getName());

                ImageLoader.getInstance().displayImage(bean.getItems().get(7).getIcon(), cateHolder.icon8);
                cateHolder.text8.setText(bean.getItems().get(7).getName());

                cateHolder.layout1.setOnClickListener(this);
                cateHolder.layout2.setOnClickListener(this);
                cateHolder.layout3.setOnClickListener(this);
                cateHolder.layout4.setOnClickListener(this);
                cateHolder.layout5.setOnClickListener(this);
                cateHolder.layout6.setOnClickListener(this);
                cateHolder.layout7.setOnClickListener(this);
                cateHolder.layout8.setOnClickListener(this);
            }

        } else if (type == 2) {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout1:
                Intent intent = new Intent(mContext, JumpControlService.class);
                intent.putExtra("url", bean.getItems().get(0).getUrl());
                mContext.startService(intent);
                break;
            case R.id.layout2:
                Intent intent1 = new Intent(mContext, JumpControlService.class);
                intent1.putExtra("url", bean.getItems().get(1).getUrl());
                mContext.startService(intent1);
                break;
            case R.id.layout3:
                Intent intent2 = new Intent(mContext, JumpControlService.class);
                intent2.putExtra("url", bean.getItems().get(2).getUrl());
                mContext.startService(intent2);
                break;
            case R.id.layout4:
                Intent intent3 = new Intent(mContext, JumpControlService.class);
                intent3.putExtra("url", bean.getItems().get(3).getUrl());
                mContext.startService(intent3);
                break;
            case R.id.layout5:
                Intent intent4 = new Intent(mContext, JumpControlService.class);
                intent4.putExtra("url", bean.getItems().get(4).getUrl());
                mContext.startService(intent4);
                break;
            case R.id.layout6:
                Intent intent5 = new Intent(mContext, JumpControlService.class);
                intent5.putExtra("url", bean.getItems().get(5).getUrl());
                mContext.startService(intent5);
                break;
            case R.id.layout7:
                Intent intent6 = new Intent(mContext, JumpControlService.class);
                intent6.putExtra("url", bean.getItems().get(6).getUrl());
                mContext.startService(intent6);
                break;
            case R.id.layout8:
                Intent intent7 = new Intent(mContext, JumpControlService.class);
                intent7.putExtra("url", bean.getItems().get(7).getUrl());
                mContext.startService(intent7);
                break;

        }
    }

    private class CategoryHolder {
        private ImageView icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8;
        private TextView text1, text2, text3, text4, text5, text6, text7, text8;
        private RelativeLayout layout1, layout2, layout3, layout4, layout5, layout6, layout7, layout8;
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
