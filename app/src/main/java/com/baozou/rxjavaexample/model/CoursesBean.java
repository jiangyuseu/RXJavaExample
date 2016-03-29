package com.baozou.rxjavaexample.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/3/29.
 * 主页数据Bean
 */
public class CoursesBean {
    private List<CourseBean> top_courses = new ArrayList<>();
    private List<CourseBean> data = new ArrayList<>();
    private long timestamp = 0;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<CourseBean> getTop_courses() {
        return top_courses;
    }

    public void setTop_courses(List<CourseBean> top_courses) {
        this.top_courses = top_courses;
    }

    public List<CourseBean> getData() {
        return data;
    }

    public void setData(List<CourseBean> data) {
        this.data = data;
    }
}
