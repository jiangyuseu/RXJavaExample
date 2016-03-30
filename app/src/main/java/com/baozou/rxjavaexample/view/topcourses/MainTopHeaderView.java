package com.baozou.rxjavaexample.view.topcourses;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseActivity;
import com.baozou.rxjavaexample.model.CourseBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created MZW QA on 2016/3/21.
 */
public class MainTopHeaderView {

    public static final int MSG_PAGER = 0x01;
    public static final long TICK = 5000;

    private Activity act;

    private List<CourseBean> top_stories = new ArrayList<>();

    /**
     * head view
     **/
    private TextView mian_heard_desc;
    private TopStoriesPagerContainer mTopPagerContainer;
    private MainTopCoursesPagerAdapter mTopPagerAdapter;
    private ViewPager mTopViewPager;
    private CirclePageIndicator mTopPageIndicator;
    private GestureDetector mTapGestureDetector;
    private View mHeaderView;
    private final PagerTimerHandler mPagerTimerHandler = new PagerTimerHandler();

    public MainTopHeaderView(Activity act, List<CourseBean> list){
        this.act = act;
        this.top_stories = list;
        headViewInit();
    }

    public View getHeaderView(){
        return mHeaderView;
    }

    /**
     * 头图的初始化
     */
    private void headViewInit() {
        mHeaderView = LayoutInflater.from(act).inflate(R.layout.activity_main_header, null);
        mian_heard_desc = (TextView) mHeaderView.findViewById(R.id.main_heard_desc);
        this.mTapGestureDetector = new GestureDetector(act, new TapGestureListener());
        this.mTopPagerAdapter = new MainTopCoursesPagerAdapter((BaseActivity) act, top_stories);
        this.mTopPagerContainer = (TopStoriesPagerContainer) mHeaderView.findViewById(R.id.topstories_pager_container);

        DisplayMetrics metrices = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(metrices);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this.mTopPagerContainer.getLayoutParams();
        params.height = metrices.widthPixels * 3 / 5;
        params.width = metrices.widthPixels;
        this.mTopPagerContainer.setLayoutParams(params);

        this.mTopViewPager = mTopPagerContainer.getViewPager();
        this.mTopViewPager.setAdapter(mTopPagerAdapter);
        this.mTopViewPager.setOffscreenPageLimit(mTopPagerAdapter.getCount());
        this.mTopViewPager.setPageMargin(0);
        this.mTopViewPager.setClipChildren(false);
        this.mTopViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mTapGestureDetector.onTouchEvent(event);
            }
        });

        this.mTopPageIndicator = (CirclePageIndicator) mHeaderView.findViewById(R.id.indicator);
        this.mTopPageIndicator.setViewPager(mTopViewPager);
        this.mTopPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                if (top_stories.size() > arg0) {
                    mian_heard_desc.setText(top_stories.get(arg0).getTitle());
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }

    public void removeHandlerMessage(){
        mPagerTimerHandler.removeMessages(MSG_PAGER);
    }

    public void sendHanderMessage(){
        mPagerTimerHandler.sendEmptyMessageDelayed(MSG_PAGER, TICK);
    }

    public void headerSetData(List<CourseBean> list){
        this.top_stories = list;
        mTopPagerAdapter.notifyDataSetChanged();
        if (top_stories.size() > 0 ){
            mian_heard_desc.setText(top_stories.get(0).getTitle());
            mTopViewPager.setCurrentItem(0);
        }
    }

    /**
     * 头图手势滑动
     */
    private class TapGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (top_stories.size() > mTopViewPager.getCurrentItem()) {
                CourseBean topStory = top_stories.get(mTopViewPager.getCurrentItem());
               //跳转单片页
            }
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent pE1, MotionEvent pE2, float pDistanceX, float pDistanceY) {
            mPagerTimerHandler.removeMessages(MSG_PAGER);
            return super.onScroll(pE1, pE2, pDistanceX, pDistanceY);
        }

        @Override
        public boolean onFling(MotionEvent pE1, MotionEvent pE2, float pVelocityX, float pVelocityY) {
            if (!mPagerTimerHandler.hasMessages(MSG_PAGER)) {
                mPagerTimerHandler.sendEmptyMessageDelayed(MSG_PAGER, TICK);
            }
            return super.onFling(pE1, pE2, pVelocityX, pVelocityY);
        }
    }

    /**
     * 头图自动滚动控制
     */
    private class PagerTimerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_PAGER) {
                if (top_stories != null && top_stories.size() > 0) {
                    List<CourseBean> newsList = top_stories;
                    if (newsList != null && newsList.size() > 0) {
                        int totalItem = top_stories.size();
                        int currentItem = mTopViewPager.getCurrentItem();
                        int setCurrentItem = (currentItem + 1) % totalItem;
                        mTopViewPager.setCurrentItem(setCurrentItem);
                    }
                }
                mPagerTimerHandler.sendEmptyMessageDelayed(MSG_PAGER, TICK);
            } else {
                super.handleMessage(msg);
            }
        }
    }
}
