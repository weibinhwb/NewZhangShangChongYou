package com.example.weibinhuang.zhangshangchongyou_wb.imp.model;

import android.content.Context;

import com.example.weibinhuang.zhangshangchongyou_wb.bean.Transaction;
import com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener.TimeTablePartListener;

import java.util.List;

import static com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener.TimeTablePartListener.*;

/**
 * Created by weibinhuang on 18-5-26.
 */

public interface ItimetablePart {

    void init(Context context);
    void getPersonTimeTable(String stuNum, String forceFetch);
    void getTransaction(String stuNum, String idNum);
    void addTransaction(String stuNum, String idNum, String time, String title, String content, String id, List<Transaction.data.date> list);
    void deleteTransaction(String stuNum, String idNum, String id);
    void setListener(GetTimeTableListener getTimeTableListener, AddTransactionListener addTransactionListener, GetTransactionListener getTransactionListener, DeleteTransactionListener deleteTransactionListener);
}
