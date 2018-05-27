package com.example.weibinhuang.zhangshangchongyou_wb.imp.presenter;

import android.content.Context;

import com.example.weibinhuang.zhangshangchongyou_wb.imp.view.IloginView;

/**
 * Created by weibinhuang on 18-5-26.
 */

public interface IuserBreifPresenter {
    void login(IloginView view, String stuNum, String idNum, Context context);
}
