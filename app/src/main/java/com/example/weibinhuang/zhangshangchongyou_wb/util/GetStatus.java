package com.example.weibinhuang.zhangshangchongyou_wb.util;

import org.json.JSONObject;

import static com.example.weibinhuang.zhangshangchongyou_wb.api.UsersPart.OK;

/**
 * Created by weibinhuang on 18-5-26.
 */

public class GetStatus {
    public static boolean getStatus(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            int status = jsonObject.getInt("status");
            if (status != OK)
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
