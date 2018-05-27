package com.example.weibinhuang.zhangshangchongyou_wb.imp.presenter;

import com.example.weibinhuang.zhangshangchongyou_wb.imp.view.IaskQuestionView;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.view.IquestionView;

/**
 * Created by weibinhuang on 18-5-26.
 */

public interface IquestionPresenter {
    void initQuestionView(IquestionView view, String tag);
    void initAskQuestionView(IaskQuestionView view, String title, String description, String isAnonymous, String kind, String reward, String disappearTime);
}
