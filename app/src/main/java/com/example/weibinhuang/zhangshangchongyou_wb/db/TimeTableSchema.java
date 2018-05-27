package com.example.weibinhuang.zhangshangchongyou_wb.db;

/**
 * Created by weibinhuang on 18-5-26.
 */

public class TimeTableSchema {

    public static final class PersonTimeTable{
        public static final String NAME = "timetable";

        public static final class Cols{
            public static final String LESSON = "lesson";
            public static final String DAY = "day";
            public static final String COURSE = "course";
            public static final String TEACHER = "teacher";
            public static final String CLASSROOM = "classroom";
            public static final String RAWWEEK = "rawWeek";
            public static final String TYPE = "type";
            public static final String WEEK = "week";
        }
    }
}
