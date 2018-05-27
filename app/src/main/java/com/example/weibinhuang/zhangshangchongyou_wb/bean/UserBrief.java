package com.example.weibinhuang.zhangshangchongyou_wb.bean;

/**
 * Created by weibinhuang on 18-5-25.
 */

public class UserBrief {
    public static String stuNum;
    public static String idNum;
    private String name;
    private String college;
    private String _class;
    private String classNum;

    public static String getStuNum() {
        return stuNum;
    }

    public static void setStuNum(String stuNum) {
        UserBrief.stuNum = stuNum;
    }

    public static String getIdNum() {
        return idNum;
    }

    public static void setIdNum(String idNum) {
        UserBrief.idNum = idNum;
    }

    private String gender;
    private String major;
    private String grade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
