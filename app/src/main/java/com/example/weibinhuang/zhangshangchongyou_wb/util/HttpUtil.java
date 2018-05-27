package com.example.weibinhuang.zhangshangchongyou_wb.util;

import android.util.Log;

import com.example.weibinhuang.zhangshangchongyou_wb.callbacklistener.CallbackListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by weibinhuang on 18-5-9.
 */

public class HttpUtil {

    private static final String TAG = "HttpUtil";
    public static void sendHttpRequset(final String address, final byte[] requestbody,final CallbackListener<String, Exception> listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(address);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestProperty("connection", "keep-alive");
                    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    OutputStream os = urlConnection.getOutputStream();
                    os.write(requestbody);
                    os.flush();
                    os.close();
                    InputStream in = urlConnection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    if (listener != null){
                        listener.onFinish(response.toString());
                    }
                    Log.d(TAG, response.toString() + "");
                } catch (Exception e){
                    if (listener != null){
                        listener.onError(e);
                    }
                } finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            }
        }).start();
    }
}
