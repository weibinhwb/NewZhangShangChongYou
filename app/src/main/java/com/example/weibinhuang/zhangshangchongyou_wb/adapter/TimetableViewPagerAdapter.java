package com.example.weibinhuang.zhangshangchongyou_wb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.weibinhuang.zhangshangchongyou_wb.view.frag.TimeTableFragment;

/**
 * Created by weibinhuang on 18-5-27.
 */

public class TimetableViewPagerAdapter extends FragmentPagerAdapter {

    public TimetableViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new TimeTableFragment();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
