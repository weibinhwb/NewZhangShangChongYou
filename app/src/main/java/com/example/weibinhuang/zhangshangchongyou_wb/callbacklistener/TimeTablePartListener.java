package com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener;

import com.example.weibinhuang.zhangshangchongyou_wb.bean.TimeTable;
import com.example.weibinhuang.zhangshangchongyou_wb.bean.Transaction;

import java.util.List;


/**
 * Created by weibinhuang on 18-5-26.
 */

public interface TimeTablePartListener {

    interface GetTimeTableListener {
        void getTimeTableSuccess(List<TimeTable.TimeTableDetail> list);
        void getTimeTableFailed();
    }

    interface GetTransactionListener{
        void getTransactionSuccess(List<Transaction.data> dataList);
        void getTransactionFailed();
    }

    interface AddTransactionListener{
        void addTransactionSuccess();
        void addTransactionFailed();
    }

    interface DeleteTransactionListener{
        void deleteTransactionSuccess();
        void deleteTransactionFailed();
    }
}
