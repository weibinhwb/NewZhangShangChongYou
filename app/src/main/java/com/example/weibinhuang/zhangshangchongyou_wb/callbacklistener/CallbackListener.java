package com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener;

/**
 * Created by weibinhuang on 18-5-9.
 */

public interface CallbackListener<T, V>{

    void onFinish(T response);
    void onError(V e);
}
