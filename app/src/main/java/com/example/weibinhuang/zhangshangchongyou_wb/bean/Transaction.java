package com.example.weibinhuang.zhangshangchongyou_wb.bean;

import java.util.List;

/**
 * Created by weibinhuang on 18-5-26.
 */

public class Transaction {
    private String term;
    private String stuNum;
    public data mData;

    public class data{
        private long id;
        private int time;
        private String title;
        private String content;
        private String updatedTime;
        public date mDate;
        private List<date> mDateList;

        public List<date> getDateList() {
            return mDateList;
        }

        public void setDateList(List<date> dateList) {
            mDateList = dateList;
        }

        public class date{
            private int _class;
            private int day;
            private List<Integer> week;

            public int get_class() {
                return _class;
            }

            public void set_class(int _class) {
                this._class = _class;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public List<Integer> getWeek() {
                return week;
            }

            public void setWeek(List<Integer> week) {
                this.week = week;
            }
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            this.updatedTime = updatedTime;
        }

        public date getDate() {
            return mDate;
        }

        public void setDate(date date) {
            mDate = date;
        }
    }

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

    public data getData() {
        return mData;
    }

    public void setData(data data) {
        mData = data;
    }
}
