package com.example.weibinhuang.zhangshangchongyou_wb.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.weibinhuang.zhangshangchongyou_wb.bean.TimeTable;

import java.util.ArrayList;
import java.util.List;

import static com.example.weibinhuang.zhangshangchongyou_wb.db.TimeTableSchema.*;
import static com.example.weibinhuang.zhangshangchongyou_wb.db.TimeTableSchema.PersonTimeTable.*;

/**
 * Created by weibinhuang on 18-5-26.
 */

public class TimeTableCursorWrapper extends CursorWrapper {

    public TimeTableCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public TimeTable.TimeTableDetail getTimeTable(){
        String day = getString(getColumnIndex(Cols.DAY));
        String lesson = getString(getColumnIndex(Cols.LESSON));
        String course = getString(getColumnIndex(Cols.COURSE));
        String teacher = getString(getColumnIndex(Cols.TEACHER));
        String classroom = getString(getColumnIndex(Cols.CLASSROOM));
        String rawWeek = getString(getColumnIndex(Cols.RAWWEEK));
        String type = getString(getColumnIndex(Cols.TYPE));
        //List<Integer> week = ;

        TimeTable.TimeTableDetail timeTableDetail = new TimeTable().new TimeTableDetail();
        timeTableDetail.setType(type);
        timeTableDetail.setDay(day);
        timeTableDetail.setLesson(lesson);
        timeTableDetail.setCourse(course);
        timeTableDetail.setTeacher(teacher);
        timeTableDetail.setClassroom(classroom);
        timeTableDetail.setRawWeek(rawWeek);

        return timeTableDetail;
    }
}
