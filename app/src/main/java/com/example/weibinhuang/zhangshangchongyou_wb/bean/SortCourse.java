package com.example.weibinhuang.zhangshangchongyou_wb.bean;

public class SortCourse {
    private String lesson;
    private String course;
    private String teacher;
    private String day;
    private String classroom;
    private int CurrentWeek;
    private int beginLesson;
    private int nowWeek;

    public int getNowWeek() {
        return nowWeek;
    }

    public void setNowWeek(int nowWeek) {
        this.nowWeek = nowWeek;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public int getCurrentWeek() {
        return CurrentWeek;
    }

    public void setCurrentWeek(int currentWeek) {
        CurrentWeek = currentWeek;
    }

    public int getBeginLesson() {
        return beginLesson;
    }

    public void setBeginLesson(int beginLesson) {
        this.beginLesson = beginLesson;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
