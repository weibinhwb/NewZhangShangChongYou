package com.example.weibinhuang.zhangshangchongyou_wb.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.weibinhuang.zhangshangchongyou_wb.view.frag.TimeTableFragment;

import static com.example.weibinhuang.zhangshangchongyou_wb.model.TimeTableModel.TOTALWEEKS;

/**
 * Created by weibinhuang on 18-5-27.
 */

public class TimetableViewPagerAdapter extends FragmentPagerAdapter {

    public static final String CURRENT_WEEK = "currentWeek";
    public TimetableViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new TimeTableFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CURRENT_WEEK, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 17;
    }
}
