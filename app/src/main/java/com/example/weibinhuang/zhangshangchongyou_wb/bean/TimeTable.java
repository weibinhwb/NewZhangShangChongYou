package com.example.weibinhuang.zhangshangchongyou_wb.bean;

import java.util.List;

/**
 * Created by weibinhuang on 18-5-26.
 */

public class TimeTable {
    private String term;
    private String stuNum;
    private String cachedTimestamp;
    private String outOfDateTimestamp;
    private int nowWeek;
    public TimeTableDetail mTableDetail;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getCachedTimestamp() {
        return cachedTimestamp;
    }

    public void setCachedTimestamp(String cachedTimestamp) {
        this.cachedTimestamp = cachedTimestamp;
    }

    public String getOutOfDateTimestamp() {
        return outOfDateTimestamp;
    }

    public void setOutOfDateTimestamp(String outOfDateTimestamp) {
        this.outOfDateTimestamp = outOfDateTimestamp;
    }

    public int getNowWeek() {
        return nowWeek;
    }

    public void setNowWeek(int nowWeek) {
        this.nowWeek = nowWeek;
    }

    public class TimeTableDetail {
        private int hashDay;
        private int hashLesson;
        private int beginLession;
        private String day;
        private String lesson;
        private String course;
        private String teacher;
        private String classroom;
        private String rawWeek;
        private String weekModel;
        private int weekBegin;
        private int weekEnd;
        private String type;
        private int period;
        private String _id;
        private List<Integer> week;

        public int getHashDay() {
            return hashDay;
        }

        public void setHashDay(int hashDay) {
            this.hashDay = hashDay;
        }

        public int getHashLesson() {
            return hashLesson;
        }

        public void setHashLesson(int hashLesson) {
            this.hashLesson = hashLesson;
        }

        public int getBeginLession() {
            return beginLession;
        }

        public void setBeginLession(int beginLession) {
            this.beginLession = beginLession;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
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

        public String getRawWeek() {
            return rawWeek;
        }

        public void setRawWeek(String rawWeek) {
            this.rawWeek = rawWeek;
        }

        public String getWeekModel() {
            return weekModel;
        }

        public void setWeekModel(String weekModel) {
            this.weekModel = weekModel;
        }

        public int getWeekBegin() {
            return weekBegin;
        }

        public void setWeekBegin(int weekBegin) {
            this.weekBegin = weekBegin;
        }

        public int getWeekEnd() {
            return weekEnd;
        }

        public void setWeekEnd(int weekEnd) {
            this.weekEnd = weekEnd;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getPeriod() {
            return period;
        }

        public void setPeriod(int period) {
            this.period = period;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public List<Integer> getWeek() {
            return week;
        }

        public void setWeek(List<Integer> week) {
            this.week = week;
        }
    }
}
