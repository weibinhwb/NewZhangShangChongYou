package com.example.weibinhuang.zhangshangchongyou_wb.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.weibinhuang.zhangshangchongyou_wb.view.frag.QuestionFragment;

/**
 * Created by weibinhuang on 18-5-26.
 */

public class QuestionViewPagerAdapter extends FragmentPagerAdapter {

    private String[] mTag = {"全部", "学习","情感","其他"};

    public QuestionViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new QuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TAG", mTag[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTag.length;
    }
}
