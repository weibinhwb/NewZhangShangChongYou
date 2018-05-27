package com.example.weibinhuang.zhangshangchongyou_wb.imp.model;

import com.example.weibinhuang.zhangshangchongyou_wb.bean.UserBrief;
import com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener.CallbackListener;

/**
 * Created by weibinhuang on 18-5-26.
 */

public interface IuserPart {
    void verifyUser(String stuNum, String idNum);
    void setLister(CallbackListener<UserBrief, String> listener);
}
