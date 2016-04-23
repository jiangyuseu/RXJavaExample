package com.baozou.rxjavaexample.model;

import java.io.Serializable;

/**
 * Created by jiangyu on 2016/3/28.
 * 标准格式的teacherbean
 */
public class TeacherBean implements Serializable {
    private static final long serialVersionUID = 8782065797339541614L;
    private String name;
    private int tid;
    private String avatar;
    private int age;
    private int teachAge;
    private String experience;
    private int sex; // 1男，0女
    private int isVerify; //1认证，0未认证

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTeachAge() {
        return teachAge;
    }

    public void setTeachAge(int teachAge) {
        this.teachAge = teachAge;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(int isVerify) {
        this.isVerify = isVerify;
    }
}
