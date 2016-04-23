package com.baozou.rxjavaexample.model;

import java.io.Serializable;

/**
 * Created by jiangyu on 2016/3/28.
 * 标准的课程bean
 */
public class CourseBean implements Serializable{

    private static final long serialVersionUID = -1019643443881559865L;

    private int cid;
    private String name;
    private String title;
    private String location;
    private TeacherBean teacher;
    private String image;
    private String thumbnail;
    private int sign;
    private int total;
    private double price;
    private String address;


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TeacherBean getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherBean teacher) {
        this.teacher = teacher;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
