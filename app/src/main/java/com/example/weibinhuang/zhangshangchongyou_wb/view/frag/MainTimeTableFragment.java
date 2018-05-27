package com.example.weibinhuang.zhangshangchongyou_wb.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.weibinhuang.zhangshangchongyou_wb.R;
import com.example.weibinhuang.zhangshangchongyou_wb.adapter.TimetableViewPagerAdapter;

/**
 * Created by weibinhuang on 18-5-27.
 */

public class MainTimeTableFragment extends Fragment {

    private ViewPager mViewPager;
    private TimetableViewPagerAdapter mTimetableViewPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_timetable_main, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.timetable_viewpager);
        mTimetableViewPagerAdapter = new TimetableViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mTimetableViewPagerAdapter);
        return view;
    }
}
