package com.baozou.rxjavaexample.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/3/29.
 * 主页数据Bean
 */
public class CoursesBean {
    private List<CourseBean> topCourses = new ArrayList<>();
    private List<CourseBean> datas = new ArrayList<>();
    private List<ItemBean> items = new ArrayList<>();

    private long timestamp = 0;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<CourseBean> getTop_courses() {
        return topCourses;
    }

    public void setTop_courses(List<CourseBean> top_courses) {
        this.topCourses = top_courses;
    }

    public List<CourseBean> getData() {
        return datas;
    }

    public void setData(List<CourseBean> data) {
        this.datas = data;
    }

    public List<ItemBean> getItems() {
        return items;
    }

    public void setItems(List<ItemBean> items) {
        this.items = items;
    }
}
