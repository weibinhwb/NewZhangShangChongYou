package com.example.weibinhuang.zhangshangchongyou_wb.model;

import com.example.weibinhuang.zhangshangchongyou_wb.imp.api.QuestionPart;
import com.example.weibinhuang.zhangshangchongyou_wb.bean.Question;
import com.example.weibinhuang.zhangshangchongyou_wb.bean.QuestionDetailedInfo;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.model.IquestionPart;
import com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener.CallbackListener;
import com.example.weibinhuang.zhangshangchongyou_wb.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.weibinhuang.zhangshangchongyou_wb.bean.UserBrief.idNum;
import static com.example.weibinhuang.zhangshangchongyou_wb.bean.UserBrief.stuNum;
import static com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener.QuestionPartListener.*;
import static com.example.weibinhuang.zhangshangchongyou_wb.util.GetStatus.getStatus;

/**
 *
 * Created by weibinhuang on 18-5-26.
 */

public class QuestionModel implements IquestionPart, QuestionPart{

    private List<Question> mQuestionList = new ArrayList<>();
    private List<QuestionDetailedInfo.QuestionAnswer> mAnswerList = new ArrayList<>();
    private QuestionDetailedInfo mQuestionDetailedInfo;

    private GetQuestionListListener mQuestionListListener;
    private AddQuestionListener mAddQuestionListener;
    private CancelQuestionListener mCancelQuestionListener;
    private GetDetailedInfoListener mGetDetailedInfoListener;
    private UpLoadPictureListener mUpLoadPictureListener;
    private static final String TAG = "QuestionModel";

    @Override
    public void AddQuestion(String title, String description, String isAnonymous, String kind, String reward, String disappearTime) {
        byte[] param = ("stuNum=" + stuNum + "&idNum=" + idNum + "&title=" + title + "&description=" + description + "&is_anonymous=" + isAnonymous
                    + "&kind=" + kind + "&tags=PHP"+ "&reward=" + reward + "&disappear_time=" + disappearTime).getBytes();
        HttpUtil.sendHttpRequset(ADDQUESTION, param, new CallbackListener<String, Exception>() {
            @Override
            public void onFinish(String response) {
                if (getStatus(response)){
                    mAddQuestionListener.addSuccess();
                } else {
                    mAddQuestionListener.addFailed();
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void GetQuestionList(String page, String size, String kind) {
        byte[] param = ("page=" + page + "&size=" + size + "&kind=" + kind).getBytes();
        HttpUtil.sendHttpRequset(GETQUESTIONLIST, param, new CallbackListener<String, Exception>() {
            @Override
            public void onFinish(String response) {
                if (getStatus(response)){
                    parseGetQuestionListJson(response);
                    mQuestionListListener.onFinish(mQuestionList);
                } else {
                    mQuestionListListener.onError("加载失败");
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void parseGetQuestionListJson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("data");
            setQuestionList(data);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void setQuestionList(JSONArray data) throws JSONException{
        JSONObject questionObject;
        for (int i = 0; i < data.length(); i ++){
            Question question = new Question();
            questionObject = data.getJSONObject(i);
            question.setTitle( questionObject.getString("title"));
            question.setDescription( questionObject.getString("description"));
            question.setKind( questionObject.getString("kind"));
            question.setReward( questionObject.getInt("reward"));
            question.setAnswer_num( questionObject.getInt("answer_num"));
            question.setDisappear_at( questionObject.getString("disappear_at"));
            question.setCreated_at( questionObject.getString("created_at"));
            question.setIs_anonymous( questionObject.getInt("is_anonymous"));
            question.setId( questionObject.getInt("id"));
            question.setPhoto_thumbnail_src( questionObject.getString("photo_thumbnail_src"));
            question.setNickname( questionObject.getString("nickname"));
            question.setGender( questionObject.getString("gender"));
            mQuestionList.add(question);
        }
    }

    @Override
    public void DeleteQuestion(String questionId) {
        byte[] param = ("stuNum=" + stuNum + "&idNum=" + idNum + "&question_id=" + questionId).getBytes();
        HttpUtil.sendHttpRequset(CANCELQUESTION, param, new CallbackListener<String, Exception>() {
            @Override
            public void onFinish(String response) {
                if(getStatus(response)){
                    mCancelQuestionListener.deleteSuccess();
                } else {
                    mCancelQuestionListener.deleteFailed();
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void QuestionDetail(String questionId) {
        byte[] param = ("stuNum=" + stuNum + "&idNum=" + idNum + "&question_id=" + questionId).getBytes();
        HttpUtil.sendHttpRequset(GETDETAILEDINFO, param, new CallbackListener<String, Exception>() {
            @Override
            public void onFinish(String response) {
                if (getStatus(response)){
                    parseDetailedInfoJson(response);
                    mGetDetailedInfoListener.getDetailedInfoSuccess(mQuestionDetailedInfo, mAnswerList);
                } else {
                    mGetDetailedInfoListener.getDetailedInfoFailed();
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void parseDetailedInfoJson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject data = jsonObject.getJSONObject("data");
            setDetailedInfoList(data);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
    private void setDetailedInfoList (JSONObject data) throws JSONException{
        mQuestionDetailedInfo = new QuestionDetailedInfo();
        JSONArray answers = data.getJSONArray("answers");
        QuestionDetailedInfo.QuestionAnswer questionAnswer = mQuestionDetailedInfo.new QuestionAnswer();
        mQuestionDetailedInfo.setTitle(data.getString("title"));
        mQuestionDetailedInfo.setDescription(data.getString("description"));
        mQuestionDetailedInfo.setReward(data.getString("reward"));
        mQuestionDetailedInfo.setDisappear_at(data.getString("disappear_at"));
        mQuestionDetailedInfo.setKind(data.getString("kind"));
       // questionDetailedInfo.setPhoto_urls(data.getString("photo_urls"));
        mQuestionDetailedInfo.setQuestioner_nickname(data.getString("questioner_nickname"));
        mQuestionDetailedInfo.setQuestioner_photo_thumbnail_src(data.getString("questioner_photo_thumbnail_src"));
        mQuestionDetailedInfo.setQuestioner_gender(data.getString("questioner_gender"));
        for (int i = 0; i < answers.length(); i ++){
            JSONObject answer = answers.getJSONObject(i);
            questionAnswer.setId(answer.getString("id"));
            questionAnswer.setNickname(answer.getString("nickname"));
            questionAnswer.setPhoto_thumbnail_src(answer.getString("photo_thumbnail_src"));
            questionAnswer.setGender(answer.getString("gender"));
            questionAnswer.setContext(answer.getString("content"));
            questionAnswer.setCreated_at(answer.getString("created_at"));
            questionAnswer.setPraise_num(answer.getString("praise_num"));
            questionAnswer.setComment_num(answer.getString("comment_num"));
            questionAnswer.setIs_adopted(answer.getString("is_adopted"));
            questionAnswer.setIs_praised(answer.getString("is_praised"));
            //
            mAnswerList.add(questionAnswer);
        }
    }

    @Override
    public void UpLoadPicture(String questionId) {

    }

    @Override
    public void setLister(GetQuestionListListener getQuestionListListener, AddQuestionListener addQuestionListener, CancelQuestionListener cancelQuestionListener, GetDetailedInfoListener getDetailedInfoListener, UpLoadPictureListener upLoadPictureListener) {
            mAddQuestionListener = addQuestionListener;
            mGetDetailedInfoListener = getDetailedInfoListener;
            mQuestionListListener = getQuestionListListener;
            mCancelQuestionListener = cancelQuestionListener;
            mUpLoadPictureListener = upLoadPictureListener;
    }

}
