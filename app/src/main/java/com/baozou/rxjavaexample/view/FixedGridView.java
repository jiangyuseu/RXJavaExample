package com.baozou.rxjavaexample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by sunfusheng on 16/4/21.
 * 解决scrollView嵌套gridView时，gridView显示不全的问题
 */
public class FixedGridView extends GridView {

    public FixedGridView(Context context) {
        super(context);
    }

    public FixedGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
