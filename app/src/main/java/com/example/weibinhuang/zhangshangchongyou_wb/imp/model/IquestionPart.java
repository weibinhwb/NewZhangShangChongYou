package com.example.weibinhuang.zhangshangchongyou_wb.imp.model;

import static com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener.QuestionPartListener.*;

/**
 * Created by weibinhuang on 18-5-26.
 */

public interface IquestionPart {
    void AddQuestion(String title, String description, String isAnonymous,
                     String kind, String reward, String disappearTime);
    void GetQuestionList(String page, String size, String kind);
    void DeleteQuestion(String questionId);
    void QuestionDetail(String questionId);
    void UpLoadPicture(String questionId);
    void setLister(GetQuestionListListener getQuestionListListener, AddQuestionListener addQuestionListener
                    ,CancelQuestionListener cancelQuestionListener, GetDetailedInfoListener getDetailedInfoListener, UpLoadPictureListener upLoadPictureListener);
}
