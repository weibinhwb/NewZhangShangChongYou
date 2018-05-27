package com.example.weibinhuang.zhangshangchongyou_wb.model;

import com.example.weibinhuang.zhangshangchongyou_wb.bean.UserBrief;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.model.IuserPart;
import com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener.CallbackListener;
import com.example.weibinhuang.zhangshangchongyou_wb.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.weibinhuang.zhangshangchongyou_wb.api.UsersPart.OK;
import static com.example.weibinhuang.zhangshangchongyou_wb.api.UsersPart.VERIFY;

/**
 *
 * Created by weibinhuang on 18-5-26.
 */

public class UserBriefModel implements IuserPart {

    private static final String TAG = "UserBriefModel";
    private UserBrief mUserBrief = new UserBrief();
    private CallbackListener<UserBrief, String> mListener;


    @Override
    public void verifyUser(String stuNum, String idNum) {
        byte[] params = ("stuNum=" + stuNum + "&idNum=" + idNum).getBytes();
        HttpUtil.sendHttpRequset(VERIFY, params, new CallbackListener<String, Exception>() {
            @Override
            public void onFinish(String response) {
                if (parseJson(response))
                    mListener.onFinish(mUserBrief);
                else{
                    mListener.onError("登录失败");
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void setLister(CallbackListener<UserBrief, String> listener) {
        mListener = listener;
    }

    private void setUserBrief(JSONObject data) throws JSONException{
        mUserBrief.set_class(data.getString("class"));
        mUserBrief.setClassNum(data.getString("classNum"));
        mUserBrief.setGender(data.getString("gender"));
        mUserBrief.setCollege(data.getString("college"));
        mUserBrief.setGrade(data.getString("grade"));
        mUserBrief.setName(data.getString("name"));
        mUserBrief.setMajor(data.getString("major"));
        UserBrief.idNum = data.getString("idNum");
        UserBrief.stuNum = data.getString("stuNum");
    }

    private boolean parseJson(String json){
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            int status = jsonObject.getInt("status");
            if (status != OK)
                return false;
            JSONObject data = jsonObject.getJSONObject("data");
            setUserBrief(data);
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
