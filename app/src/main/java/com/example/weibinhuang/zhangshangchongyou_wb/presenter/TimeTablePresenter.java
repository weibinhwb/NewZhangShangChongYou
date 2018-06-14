package com.example.weibinhuang.zhangshangchongyou_wb.presenter;

import android.content.Context;
import android.util.Log;

import com.example.weibinhuang.zhangshangchongyou_wb.bean.SortCourse;
import com.example.weibinhuang.zhangshangchongyou_wb.bean.TimeTable;
import com.example.weibinhuang.zhangshangchongyou_wb.bean.Transaction;
import com.example.weibinhuang.zhangshangchongyou_wb.bean.UserBrief;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.model.ItimetablePart;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.presenter.ItimetablePresenter;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.view.ITimeTableView;
import com.example.weibinhuang.zhangshangchongyou_wb.model.TimeTableModel;

import java.util.List;

import static com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener.TimeTablePartListener.*;

/**
 * Created by weibinhuang on 18-5-26.
 */

public class TimeTablePresenter implements ItimetablePresenter, GetTimeTableListener , AddTransactionListener, DeleteTransactionListener, GetTransactionListener{

    private static final String TAG = "TimeTablePresenter";

    private ITimeTableView mITimeTableView;

    @Override
    public void initTimeTableView(ITimeTableView view) {
        mITimeTableView = view;
        ItimetablePart itimetablePart = new TimeTableModel();
        itimetablePart.setListener(this, this, this, this);
        itimetablePart.getPersonTimeTable(UserBrief.stuNum, "false");
    }


    @Override
    public void getTimeTableSuccess(List<SortCourse> list) {
        for (int i = 0; i < list.size(); i ++){
            Log.d(TAG, list.get(i).getTeacher());
            Log.d(TAG, list.get(i).getCurrentWeek() + "");
        }
        mITimeTableView.showTimeTable(list);
    }

    @Override
    public void getTimeTableFailed() {
        Log.d(TAG, "获取失败");
    }

    @Override
    public void getTransactionSuccess(List<Transaction.data> dataList) {

    }

    @Override
    public void getTransactionFailed() {

    }

    @Override
    public void addTransactionSuccess() {

    }

    @Override
    public void addTransactionFailed() {

    }

    @Override
    public void deleteTransactionSuccess() {
        Log.d(TAG, "删除成功");
    }

    @Override
    public void deleteTransactionFailed() {
        Log.d(TAG, "删除失败");
    }
}
