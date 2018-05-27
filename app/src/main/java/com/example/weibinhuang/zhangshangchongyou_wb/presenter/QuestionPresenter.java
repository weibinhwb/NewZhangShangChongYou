package com.example.weibinhuang.zhangshangchongyou_wb.presenter;

import android.util.Log;

import com.example.weibinhuang.zhangshangchongyou_wb.bean.Question;
import com.example.weibinhuang.zhangshangchongyou_wb.bean.QuestionDetailedInfo;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.view.IaskQuestionView;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.model.IquestionPart;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.presenter.IquestionPresenter;
import com.example.weibinhuang.zhangshangchongyou_wb.model.QuestionModel;
import com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener.CallbackListener;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.view.IquestionView;

import java.util.List;

import static com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener.QuestionPartListener.*;

/**
 *
 * Created by weibinhuang on 18-5-26.
 */

public class QuestionPresenter implements IquestionPresenter, CallbackListener<List<Question>,String> , AddQuestionListener, CancelQuestionListener, GetQuestionListListener, GetDetailedInfoListener, UpLoadPictureListener{

    private static final String TAG = "QuestionPresenter";
    private IquestionView mIQuestionFragment;
    private IaskQuestionView mIAskQuestionView;
    private IquestionPart mIquestionPart;
    private String mTag;


    @Override
    public void initQuestionView(IquestionView view, String tag) {
        mIquestionPart = new QuestionModel();
        mIquestionPart.setLister(this, this, this, this, this);
        mIquestionPart.GetQuestionList("0", "10", tag);
        mIQuestionFragment = view;
    }

    @Override
    public void initAskQuestionView(IaskQuestionView view, String title, String description, String isAnonymous, String kind, String reward, String disappearTime) {
        mIAskQuestionView = view;
        mIquestionPart = new QuestionModel();
        mIquestionPart.setLister(this,this, this, this, this);
        mIquestionPart.AddQuestion(title, description, isAnonymous, kind, reward, disappearTime);
    }

    @Override
    public void onFinish(List<Question> response) {
        for (int i = 0; i < response.size(); i ++){
            Log.d(TAG, response.get(i).getId() + "");
        }
        mIQuestionFragment.showRecyclerview(response);
    }

    @Override
    public void onError(String e) {

    }
    @Override
    public void addSuccess() {
        Log.d("TAG", "添加成功");
    }

    @Override
    public void addFailed() {

    }

    @Override
    public void deleteSuccess() {
        Log.d("TAG", "删除成功");
    }

    @Override
    public void deleteFailed() {
        Log.d("TAG", "删除失败");
    }

    @Override
    public void getDetailedInfoSuccess(QuestionDetailedInfo questionDetailedInfo, List<QuestionDetailedInfo.QuestionAnswer> questionAnswer) {
        Log.d(TAG, questionDetailedInfo.getTitle());
    }

    @Override
    public void getDetailedInfoFailed() {
        Log.d(TAG, "获取失败");
    }

    @Override
    public void upLoadPictureSuccess() {

    }

    @Override
    public void upLoadPictureFailed() {

    }
}
