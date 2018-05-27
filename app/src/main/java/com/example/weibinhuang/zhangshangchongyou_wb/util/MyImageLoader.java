package com.example.weibinhuang.zhangshangchongyou_wb.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;


/**
 * 
 * Created by weibinhuang on 18-5-4.
 */

public class MyImageLoader {

    private static final String TAG = "MyImageLoader";
    private String mBitmapDirePath;
    private LruCache<String, Bitmap> mMemorycache;
    private static MyImageLoader sInstance;

    private ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private Handler mHandler;

    private MyImageLoader (Context context){
        mHandler = new Handler(Looper.getMainLooper());
        mBitmapDirePath = context.getCacheDir().getPath() + File.separator + "bitmapDirectory";
        int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
        mMemorycache = new LruCache<String, Bitmap>(maxMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes();
            }
        };
    }

    public static MyImageLoader with(Context context){
         if (sInstance == null){
             synchronized ( MyImageLoader.class){
                 if (sInstance == null){
                     sInstance = new MyImageLoader(context);
                 }
             }
         }
         return sInstance;
    }

    public getBitmap load(String urlString){
        Log.d(TAG, "load");
        return new getBitmap(urlString);
    }

    public class getBitmap implements Runnable{
        ImageView mImageView;
        String url;
        Bitmap bitmap;
        int reqWidth, reqHeigh;
        private getBitmap(String urlString){
            url = urlString;
        }
        public void into (ImageView view, int reqWidth, int reqHeight){
            this.mImageView = view;
            this.reqHeigh = reqHeight;
            this.reqWidth = reqWidth;
            mImageView.setTag(url);
            bitmap = getBitmapFromMemory(url);
            if (bitmap != null){
                mImageView.setImageBitmap(bitmap);
                return;
            }
            bitmap = getBitmapFromDisk(url, reqWidth, reqHeight);
            if (bitmap != null){
                putBitmapToMemory(bitmap, url);
                mImageView.setImageBitmap(bitmap);
                return;
            }
            mExecutorService.submit(this);
            Log.d(TAG, "into");
        }

        @Override
        public void run() {
            bitmap = getBitmapFromHttp(url, reqWidth, reqHeigh);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (mImageView.getTag() == url){
                        mImageView.setImageBitmap(bitmap);
                    }
                }
            });
            putBitmapToDisk(bitmap, url);
            putBitmapToMemory(bitmap, url);
        }
    }

    private Bitmap getBitmapFromHttp(String urlString, int reqWidth, int reqHeight){
        Bitmap bitmap = null;
        try {
            urlString = "https" + urlString.substring(4, urlString.length());
            URL url = new URL(urlString);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            InputStream is = urlConnection.getInputStream();
            Log.d(TAG, "" + is);
            BufferedInputStream bis = new BufferedInputStream(is);
            bitmap = MyImageResizer.decodeSampledBitmapFromInputStream(bis, reqWidth, reqHeight);
            urlConnection.disconnect();
            is.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        Log.d(TAG, "getBitmapFromHttp");
        return bitmap;
    }

    private Bitmap getBitmapFromDisk(String urlString, int reqWidth, int reqHeight){
        Bitmap bitmap = null;
        String key = hashKeyFormUrl(urlString);
        String bitmapFilePath = mBitmapDirePath + File.separator + key;
        File bitmapFile = new File(bitmapFilePath);
        if (!bitmapFile.exists()){
            return null;
        }
        try {
            InputStream is = new FileInputStream(bitmapFile);
            BufferedInputStream bis = new BufferedInputStream(is);
            bitmap = MyImageResizer.decodeSampledBitmapFromInputStream(bis, reqWidth, reqHeight);
            is.close();
            bis.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        Log.d(TAG, "getBitmapFromDisk");
        return bitmap;
    }

    private Bitmap getBitmapFromMemory(String urlString){
        Log.d(TAG, "getBitmapFromMemory");
        String key = hashKeyFormUrl(urlString);
        return mMemorycache.get(key);
    }

    private void putBitmapToDisk(Bitmap bitmap, String urlString){
        String key = hashKeyFormUrl(urlString);
        String bitmapFilePath = mBitmapDirePath + File.separator + key;
        File bitmapDire = new File(mBitmapDirePath);
        File bitmapFile = new File(bitmapFilePath);
        if(! bitmapDire.exists()){
            bitmapDire.mkdirs();
        }
        if(! bitmapFile.exists()){
            try {
                bitmapFile.createNewFile();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bitmapByte = baos.toByteArray();
        try {
            OutputStream outputStream = new FileOutputStream(bitmapFile);
            outputStream.write(bitmapByte);
            outputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        Log.d(TAG," putBitmapToDisk");
    }

    private void putBitmapToMemory(Bitmap bitmap, String urlString){
        String key = hashKeyFormUrl(urlString);
        if (getBitmapFromMemory(key) == null)
            mMemorycache.put(key, bitmap);
        Log.d(TAG, "putBitmapToMemory");
    }

    private String hashKeyFormUrl(String url) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i ++){
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1){
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
