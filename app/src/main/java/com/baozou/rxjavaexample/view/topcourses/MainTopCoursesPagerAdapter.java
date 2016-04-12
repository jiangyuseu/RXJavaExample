package com.baozou.rxjavaexample.view.topcourses;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseActivity;
import com.baozou.rxjavaexample.model.CourseBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainTopCoursesPagerAdapter extends PagerAdapter {

    private LayoutInflater mInflater;
    private BaseActivity mActivity;
    private List<CourseBean> mTopStoryList = null;
    private DisplayImageOptions mImageOptions;
    private ImageLoader mImageLoader;

    public MainTopCoursesPagerAdapter(BaseActivity pActivity, List<CourseBean> mTopStoryList) {
        this.mActivity = pActivity;
        this.mImageLoader = ImageLoader.getInstance();
        this.mTopStoryList = mTopStoryList;
        this.mInflater = LayoutInflater.from(pActivity);
        this.mImageOptions = new DisplayImageOptions.Builder().cacheOnDisc(true).build();
    }

    public void setData(List<CourseBean> bean){
        this.mTopStoryList = bean;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        CourseBean topStory = mTopStoryList.get(position);
        View view = mInflater.inflate(R.layout.activity_main_header_topstory_pager, null);
        ImageView topStoryImageView = (ImageView) view.findViewById(R.id.topstory_image);
        topStoryImageView.setScaleType(ScaleType.CENTER_CROP);
        this.mImageLoader.displayImage(topStory.getImage(), topStoryImageView, this.mImageOptions);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mTopStoryList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

}
