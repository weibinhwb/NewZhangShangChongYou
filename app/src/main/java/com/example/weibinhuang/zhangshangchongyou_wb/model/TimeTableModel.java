package com.example.weibinhuang.zhangshangchongyou_wb.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.weibinhuang.zhangshangchongyou_wb.bean.TimeTable;
import com.example.weibinhuang.zhangshangchongyou_wb.bean.Transaction;
import com.example.weibinhuang.zhangshangchongyou_wb.db.TimeTableBaseHelper;
import com.example.weibinhuang.zhangshangchongyou_wb.db.TimeTableCursorWrapper;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.model.ItimetablePart;
import com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener.CallbackListener;
import com.example.weibinhuang.zhangshangchongyou_wb.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.weibinhuang.zhangshangchongyou_wb.api.TimeTablePart.ADDTRANSACTION;
import static com.example.weibinhuang.zhangshangchongyou_wb.api.TimeTablePart.DELETETRANSACTION;
import static com.example.weibinhuang.zhangshangchongyou_wb.api.TimeTablePart.GETRANSACTION;
import static com.example.weibinhuang.zhangshangchongyou_wb.api.TimeTablePart.KEBIAO;
import static com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener.TimeTablePartListener.*;
import static com.example.weibinhuang.zhangshangchongyou_wb.db.TimeTableSchema.*;
import static com.example.weibinhuang.zhangshangchongyou_wb.db.TimeTableSchema.PersonTimeTable.*;
import static com.example.weibinhuang.zhangshangchongyou_wb.util.GetStatus.getStatus;

/**
 *
 * Created by weibinhuang on 18-5-26.
 */

public class TimeTableModel implements ItimetablePart{

    private static final String TAG = "TimeTableModel";

    private GetTimeTableListener mGetTimeTableListener;
    private AddTransactionListener mAddTransactionListener;
    private GetTransactionListener mGetTransactionListener;
    private DeleteTransactionListener mDeleteTransactionListener;
    private TimeTable timeTable;

    private List<TimeTable.TimeTableDetail> mTableDetailList = new ArrayList<>();
    private List<Transaction.data> mDataList = new ArrayList<>();

    private SQLiteDatabase mDatabase;

    @Override
    public void init(Context context) {
        mDatabase = new TimeTableBaseHelper(context).getWritableDatabase();
        setTimeTableDetails();
    }

    @Override
    public void getPersonTimeTable(String stuNum, String forceFetch) {
            byte[] param = ("stu_num=" + stuNum + "&forceFetch=" + forceFetch).getBytes();
            HttpUtil.sendHttpRequset(KEBIAO, param, new CallbackListener<String, Exception>() {
                @Override
                public void onFinish(String response) {
                    if (getStatus(response)){
                        parseTimeTableJson(response);
                        mGetTimeTableListener.getTimeTableSuccess(mTableDetailList);
//                        keepTimeTableDetails();
                    }
                    Log.d(TAG, response);
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            });
    }

    private void parseTimeTableJson(String json){
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("data");
            timeTable = new TimeTable();
            timeTable.setTerm(jsonObject.getString("term"));
            timeTable.setStuNum(jsonObject.getString("stuNum"));
            timeTable.setNowWeek(jsonObject.getInt("nowWeek"));
            setTimeTableDetails(data);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
    private void setTimeTableDetails(JSONArray data) throws JSONException{

        JSONObject jsonObject;
        for (int i = 0; i < data.length(); i ++){
            jsonObject = data.getJSONObject(i);
            timeTable.mTableDetail = timeTable.new TimeTableDetail();
            timeTable.mTableDetail.setHashDay(jsonObject.getInt("hash_day"));
            timeTable.mTableDetail.setHashLesson(jsonObject.getInt("hash_lesson"));
            timeTable.mTableDetail.setBeginLession(jsonObject.getInt("begin_lesson"));
            timeTable.mTableDetail.setDay(jsonObject.getString("day"));
            timeTable.mTableDetail.setLesson(jsonObject.getString("lesson"));
            timeTable.mTableDetail.setCourse(jsonObject.getString("course"));
            timeTable.mTableDetail.setTeacher(jsonObject.getString("teacher"));
            timeTable.mTableDetail.setClassroom(jsonObject.getString("classroom"));
            timeTable.mTableDetail.setRawWeek(jsonObject.getString("rawWeek"));
            timeTable.mTableDetail.setWeekModel(jsonObject.getString("weekModel"));
            timeTable.mTableDetail.setWeekBegin(jsonObject.getInt("weekBegin"));
            timeTable.mTableDetail.setWeekEnd(jsonObject.getInt("weekEnd"));
            timeTable.mTableDetail.setType(jsonObject.getString("type"));
            timeTable.mTableDetail.setPeriod(jsonObject.getInt("period"));
     //       timeTable.mTableDetail.set_id(jsonObject.getString("id"));
            JSONArray week = jsonObject.getJSONArray("week");
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < week.length(); j ++){
                list.add((Integer) week.get(j));
            }
            timeTable.mTableDetail.setWeek(list);
            mTableDetailList.add(timeTable.mTableDetail);
        }
    }

    private void keepTimeTableDetails(){
        ContentValues values;
        for (int i = 0; i < mTableDetailList.size(); i ++){
            values = getContentValues(mTableDetailList.get(i));
            mDatabase.insert(PersonTimeTable.NAME, null, values);
        }
    }

    private static ContentValues getContentValues(TimeTable.TimeTableDetail detail){
        ContentValues values = new ContentValues();
        values.put(Cols.CLASSROOM, detail.getClassroom());
        values.put(Cols.COURSE, detail.getCourse());
        values.put(Cols.DAY, detail.getDay());
        values.put(Cols.LESSON, detail.getLesson());
        values.put(Cols.RAWWEEK, detail.getRawWeek());
        values.put(Cols.TEACHER, detail.getTeacher());
        values.put(Cols.TYPE, detail.getType());
        values.put(Cols.WEEK, String.valueOf(detail.getWeek()));

        return values;
    }

    private TimeTableCursorWrapper queryTimeTable(String whereClause, String[] whereArgs){

        Cursor cursor = mDatabase.query(
                NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new TimeTableCursorWrapper(cursor);
    }

    private void setTimeTableDetails(){
        TimeTableCursorWrapper cursor = queryTimeTable(null, null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                mTableDetailList.add(cursor.getTimeTable());
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void getTransaction(String stuNum, String idNum) {
        byte[] param = ("stuNum=" + stuNum + "&idNum=" + idNum).getBytes();
        HttpUtil.sendHttpRequset(GETRANSACTION, param, new CallbackListener<String, Exception>() {
            @Override
            public void onFinish(String response) {
                if (getStatus(response)){
                    parseTransactionJson(response);
                    mGetTransactionListener.getTransactionSuccess(mDataList);
                } else {
                    mGetTransactionListener.getTransactionFailed();
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void parseTransactionJson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("data");
            setTransactionList(data);
        }  catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void setTransactionList(JSONArray data) throws JSONException{
        JSONObject jsonObject;
        Transaction transaction;
        for (int i = 0; i < data.length(); i ++){
            transaction = new Transaction();
            transaction.mData = transaction.new data();
            jsonObject = data.getJSONObject(i);
            transaction.mData.setId(jsonObject.getLong("id"));
      //      transaction.mData.setTime(jsonObject.getInt("time"));
            transaction.mData.setContent(jsonObject.getString("content"));
            transaction.mData.setUpdatedTime(jsonObject.getString("updated_time"));
            JSONArray date = jsonObject.getJSONArray("date");
            List<Transaction.data.date> dateList = new ArrayList<>();
            for (int j = 0; j < date.length(); j ++){
                JSONObject object = date.getJSONObject(j);
                transaction.mData.mDate = transaction.mData.new date();
                transaction.mData.mDate.set_class(object.getInt("class"));
                transaction.mData.mDate.setDay(object.getInt("day"));
                List<Integer> list = new ArrayList<>();
                JSONArray week = object.getJSONArray("week");
                for (int k = 0; k < week.length(); k ++){
                    list.add((Integer) week.get(k));
                }
                transaction.mData.mDate.setWeek(list);
                dateList.add(transaction.mData.mDate);
            }
            transaction.mData.setDateList(dateList);
            mDataList.add(transaction.mData);
        }
    }

    @Override
    public void addTransaction(String stuNum, String idNum, String time, String title, String content, String id, List<Transaction.data.date> list) {
        byte[] param = ("stuNum=" + stuNum + "&idNum=" + idNum + "&time=" + time + "&title=" + title + "&content=" + content + "&id=" + id + "&date=" + list).getBytes();
        HttpUtil.sendHttpRequset(ADDTRANSACTION, param, new CallbackListener<String, Exception>() {
            @Override
            public void onFinish(String response) {
                if(getStatus(response)){
                    mAddTransactionListener.addTransactionSuccess();
                } else {
                    mAddTransactionListener.addTransactionFailed();
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void deleteTransaction(String stuNum, String idNum, String id) {
        byte[] param = ("stuNum=" + stuNum + "&idNum=" + idNum + "&id=" + id).getBytes();
        HttpUtil.sendHttpRequset(DELETETRANSACTION, param, new CallbackListener<String, Exception>() {
            @Override
            public void onFinish(String response) {
                if (getStatus(response)){
                    mDeleteTransactionListener.deleteTransactionSuccess();
                } else {
                    mDeleteTransactionListener.deleteTransactionFailed();
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void setListener(GetTimeTableListener getTimeTableListener, AddTransactionListener addTransactionListener, GetTransactionListener getTransactionListener, DeleteTransactionListener deleteTransactionListener) {
        mGetTimeTableListener = getTimeTableListener;
        mAddTransactionListener = addTransactionListener;
        mDeleteTransactionListener = deleteTransactionListener;
        mGetTransactionListener = getTransactionListener;
    }
}
