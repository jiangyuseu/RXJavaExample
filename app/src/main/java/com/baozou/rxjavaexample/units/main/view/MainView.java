package com.baozou.rxjavaexample.units.main.view;

import com.baozou.rxjavaexample.model.CourseBean;
import com.baozou.rxjavaexample.model.CoursesBean;

import java.util.List;

/**
 * Created by jiangyu on 2016/5/10.
 */
public interface MainView {
    void showMainData(CoursesBean bean);

    void showHeaderData(List<CourseBean> list);
}
