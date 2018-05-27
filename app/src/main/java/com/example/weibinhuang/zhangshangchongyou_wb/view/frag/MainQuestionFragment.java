package com.example.weibinhuang.zhangshangchongyou_wb.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weibinhuang.zhangshangchongyou_wb.R;
import com.example.weibinhuang.zhangshangchongyou_wb.adapter.QuestionViewPagerAdapter;

/**
 *
 * Created by weibinhuang on 18-5-27.
 */

public class MainQuestionFragment extends Fragment{

    private ViewPager mViewPager;
    private QuestionViewPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private String[] mTag = {"全部", "学习","情感","其他"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_question_main, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.youwen_viewpager);
        mTabLayout = (TabLayout) view.findViewById(R.id.youwen_tablayout);
        mPagerAdapter = new QuestionViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTabLayout.getTabCount(); i ++){
            mTabLayout.getTabAt(i).setText(mTag[i]);
        }
        return view;
    }
}
