package com.example.weibinhuang.zhangshangchongyou_wb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.ref.PhantomReference;

import static com.example.weibinhuang.zhangshangchongyou_wb.db.TimeTableSchema.*;
import static com.example.weibinhuang.zhangshangchongyou_wb.db.TimeTableSchema.PersonTimeTable.*;

/**
 * Created by weibinhuang on 18-5-26.
 */

public class TimeTableBaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "timetableBase.db";

    public TimeTableBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NAME + "("
                + "_id integer primary key autoincrement, " + Cols.CLASSROOM + ","
                + Cols.COURSE + "," + Cols.DAY + "," + Cols.LESSON + ","
                + Cols.RAWWEEK + "," + Cols.TEACHER + "," + Cols.TYPE + ","
                + Cols.WEEK + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
