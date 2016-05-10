package com.baozou.rxjavaexample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baozou.rxjavaexample.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jiangyu on 2016/5/10.
 * 底部跳转View
 */
public class TabFooterView extends RelativeLayout{

    private View view;
    private TabClickListener listener;

    @Bind(R.id.home_icon)
    public ImageView mainImg;
    @Bind(R.id.video_icon)
    public ImageView videoImg;
    @Bind(R.id.info_icon)
    public ImageView infoImg;
    @Bind(R.id.my_icon)
    public ImageView myImg;
    @Bind(R.id.home_txt)
    public TextView mainTxt;
    @Bind(R.id.video_txt)
    public TextView videoTxt;
    @Bind(R.id.info_txt)
    public TextView infoTxt;
    @Bind(R.id.my_txt)
    public TextView myTxt;

    public TabFooterView(Context context) {
        super(context);
        initView(context);
    }

    public TabFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TabFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        view = inflate(context, R.layout.view_tab_footer, null);
        ButterKnife.bind(this,view);
        addView(view, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
    }

    @OnClick(R.id.home_layout)
    public void onHomeClick() {
        clearTabEffect();
        mainImg.setImageResource(R.mipmap.ic_home_pressed_n);
        mainTxt.setTextColor(getResources().getColor(R.color.main_style_color));
        if(listener!=null){
            listener.MainTab();
        }
    }

    @OnClick(R.id.video_layout)
    public void onVideoClick() {
        clearTabEffect();
        videoImg.setImageResource(R.mipmap.ic_my_pressed_n);
        videoTxt.setTextColor(getResources().getColor(R.color.main_text_pre));
        if(listener!=null){
            listener.VideoTab();
        }
    }

    @OnClick(R.id.info_layout)
    public void onInfoClick() {
        clearTabEffect();
        infoImg.setImageResource(R.mipmap.ic_massage_pressed_n);
        infoTxt.setTextColor(getResources().getColor(R.color.main_text_pre));
        if(listener!=null){
            listener.InfoTab();
        }
    }

    @OnClick(R.id.my_layout)
    public void onMyClick() {
        clearTabEffect();
        myImg.setImageResource(R.mipmap.ic_my_pressed_n);
        myTxt.setTextColor(getResources().getColor(R.color.main_text_pre));
        if(listener!=null){
            listener.MyTab();
        }
    }

    public void initTab(){
        clearTabEffect();
        mainImg.setImageResource(R.mipmap.ic_home_pressed_n);
        mainTxt.setTextColor(getResources().getColor(R.color.main_style_color));
    }


    private void clearTabEffect(){
        mainImg.setImageResource(R.mipmap.ic_home_normal_n);
        mainTxt.setTextColor(getResources().getColor(R.color.main_btn_no_press));
        videoImg.setImageResource(R.mipmap.ic_my_normal_n);
        videoTxt.setTextColor(getResources().getColor(R.color.main_text));
        infoImg.setImageResource(R.mipmap.ic_massage_normal_n);
        infoTxt.setTextColor(getResources().getColor(R.color.main_text));
        myImg.setImageResource(R.mipmap.ic_my_normal_n);
        myTxt.setTextColor(getResources().getColor(R.color.main_text));
    }


    public void setTabClickListener(TabClickListener l){
        this.listener = l;
    }
    public interface TabClickListener{
        public void MainTab();
        public void VideoTab();
        public void InfoTab();
        public void MyTab();
    }

}
