package com.example.weibinhuang.zhangshangchongyou_wb.presenter;

import android.content.Context;

import com.example.weibinhuang.zhangshangchongyou_wb.bean.UserBrief;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.view.IloginView;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.presenter.IuserBreifPresenter;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.model.IuserPart;
import com.example.weibinhuang.zhangshangchongyou_wb.model.UserBriefModel;
import com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener.CallbackListener;

/**
 *
 * Created by weibinhuang on 18-5-26.
 */

public class UserBriefPresenter implements IuserBreifPresenter, CallbackListener<UserBrief,String>{

    private static final String TAG = "UserBriefPresenter";
    private IloginView mIloginView;
    private IuserPart mIuserPart;
    private Context mContext;

    @Override
    public void onFinish(UserBrief response) {
        mIloginView.updateViewSuccess();
//        SharedPreferences.Editor editor = mContext.getSharedPreferences("login", Context.MODE_PRIVATE).edit();
//        editor.putString("account", UserBrief.stuNum);
//        editor.putString("passwore", UserBrief.idNum);
//        editor.apply();
    }

    @Override
    public void onError(String s) {
        mIloginView.updateViewFailed();
    }

    @Override
    public void login(IloginView view, String stuNum, String idNum, Context context) {
        mIuserPart = new UserBriefModel();
        mContext = context;
        mIuserPart.setLister(this);
        mIloginView = view;
        mIuserPart.verifyUser(stuNum, idNum);
    }
}
