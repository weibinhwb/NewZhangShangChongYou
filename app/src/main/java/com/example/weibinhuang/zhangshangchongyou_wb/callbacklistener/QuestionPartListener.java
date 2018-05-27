package com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener;

import com.example.weibinhuang.zhangshangchongyou_wb.bean.Question;
import com.example.weibinhuang.zhangshangchongyou_wb.bean.QuestionDetailedInfo;

import java.util.List;

import static com.example.weibinhuang.zhangshangchongyou_wb.bean.QuestionDetailedInfo.*;

/**
 * Created by weibinhuang on 18-5-26.
 */

public interface QuestionPartListener {

    interface GetQuestionListListener {
        void onFinish(List<Question> response);
        void onError(String msg);
    }

    interface AddQuestionListener{
        void addSuccess();
        void addFailed();
    }

    interface CancelQuestionListener{
        void deleteSuccess();
        void deleteFailed();
    }

    interface GetDetailedInfoListener{
        void getDetailedInfoSuccess(QuestionDetailedInfo questionDetailedInfo, List<QuestionDetailedInfo.QuestionAnswer> questionAnswer);
        void getDetailedInfoFailed();
    }

    interface UpLoadPictureListener{
        void upLoadPictureSuccess();
        void upLoadPictureFailed();
    }
}
