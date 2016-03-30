package com.baozou.rxjavaexample.view.topcourses;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Joosun
 * @since 2013-5-21
 */
public class InterceptViewPager extends ViewPager {
    public InterceptViewPager(final Context pContext) {
        super(pContext);
    }

    public InterceptViewPager(final Context pContext, final AttributeSet pAttributeSet) {
        super(pContext, pAttributeSet);
    }

    @Override
    public boolean dispatchTouchEvent(final MotionEvent pMotionEvent) {
        this.getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(pMotionEvent);
    }
}