package com.example.weibinhuang.zhangshangchongyou_wb.view.frag;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.weibinhuang.zhangshangchongyou_wb.R;
import com.example.weibinhuang.zhangshangchongyou_wb.bean.SortCourse;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.presenter.ItimetablePresenter;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.view.ITimeTableView;
import com.example.weibinhuang.zhangshangchongyou_wb.presenter.TimeTablePresenter;

import java.util.ArrayList;
import java.util.List;

import static com.example.weibinhuang.zhangshangchongyou_wb.view.frag.MainTimeTableFragment.TimetableViewPagerAdapter.CURRENT_WEEK;

/**
 * Created by weibinhuang on 18-5-27.
 */

public class MainTimeTableFragment extends Fragment implements View.OnClickListener{

    private ViewPager mViewPager;
    private TextView mTextView;
    TimetableViewPagerAdapter mTimetableViewPagerAdapter;
    private TabLayout mTableLayout;
    private boolean is_click = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_timetable_main, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.timetable_viewpager);
        mTableLayout = (TabLayout) view.findViewById(R.id.timetable_tablayout);
        mTextView = (TextView) view.findViewById(R.id.timetable_title);
        mTimetableViewPagerAdapter = new TimetableViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mTimetableViewPagerAdapter);
        mTableLayout.setupWithViewPager(mViewPager);
        setViewChange();
        mTableLayout.setVisibility(View.GONE);
        mTextView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (! is_click){
            Drawable drawable = getContext().getResources().getDrawable(R.drawable.show_weeks);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTextView.setCompoundDrawables(null, null, drawable, null);

            mTableLayout.setVisibility(View.VISIBLE);
            is_click = true;
        } else {
            Drawable drawable = getContext().getResources().getDrawable(R.drawable.hide_weeks);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTextView.setCompoundDrawables(null, null, drawable, null);

            mTableLayout.setVisibility(View.GONE);
            is_click = false;
        }
    }

    private void setViewChange(){
        final String weeks[] = {"第一周", "第二周", "第三周", "第四周", "第五周", "第六周", "第七周", "第八周", "第九周",
                "第十周", "第十一周", "第十二周", "第十三周", "第十四周", "第十五周", "第十六周", "第十七周", "第十八周",
                "第十九周", "第二十周", "第二十一周", "第二十二周", "第二十三周", "第二十四周", "第二十五周"};

        for (int i = 0; i < mTableLayout.getTabCount(); i ++){
            mTableLayout.getTabAt(i).setText(weeks[i]);
        }

        mTextView.setText(weeks[0]);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTextView.setText(weeks[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public static class TimetableViewPagerAdapter extends FragmentPagerAdapter {

        static final String CURRENT_WEEK = "currentWeek";
        TimetableViewPagerAdapter(FragmentManager fm) {
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
            return 25;
        }
    }

    //
    public static class TimeTableFragment extends Fragment implements ITimeTableView {

        private RecyclerView mRecyclerView;
        private TimeTableRecycleAdapter mTimeTableRecycleAdapter;
        private List<SortCourse> mSortCourses;
        private ItimetablePresenter mItimetablePresenter;
        private int currentWeek;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mItimetablePresenter = new TimeTablePresenter();
            mItimetablePresenter.initTimeTableView(this);
            Bundle bundle = getArguments();
            currentWeek = bundle.getInt(CURRENT_WEEK);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_timetable, container, false);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.timetable_recyclerview);
            mTimeTableRecycleAdapter = new TimeTableRecycleAdapter();
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setAdapter(mTimeTableRecycleAdapter);
            return view;
        }

        @Override
        public void showTimeTable(List<SortCourse> list) {
            mSortCourses = list;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTimeTableRecycleAdapter.notifyDataSetChanged();
                }
            });
        }

        class TimeTableRecycleAdapter extends RecyclerView.Adapter{

            private static final int WEEKTYPE = 0;
            private static final int TABLETYPE = 1;
            private final String[] Weeks = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
            private final String[] WeeksPlus = {"周一","周二","周三","周四","周五","周六","周日"};
            private final int[] Colors = {0 ,R.color.morning_course_color, R.color.morning_course_color, R.color.afternoon_course_color,
                                                R.color.afternoon_course_color, R.color.evening_course_color, R.color.evening_course_color};
            private final String[] CourseRank = {"","1\n\n\n2", "3\n\n\n4", "5\n\n\n6", "7\n\n\n8", "9\n\n\n10", "11\n\n\n12"};
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if (viewType == TABLETYPE){
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.item_timetable_recyclerview, parent, false);
                    return new TimeTableHolder(view);
                } else if (viewType == WEEKTYPE) {
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.item_timetable_week, parent, false);
                    return new WeekIndicatorHolder(view);
                }
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                if (holder instanceof TimeTableHolder){
                    onBindTimeTableHolder(holder, position);
                } else if (holder instanceof WeekIndicatorHolder){
                    onBindWeekIndicatorHolder(holder);
                }
            }

            private void onBindTimeTableHolder(RecyclerView.ViewHolder holder, int position){
                ((TimeTableHolder) holder).courseRank.setText(CourseRank[position]);
                List<SortCourse> currentCourse = new ArrayList<>();
                for (int i = 0; i < mSortCourses.size(); i ++){
                    SortCourse course = mSortCourses.get(i);
                    if (course.getBeginLesson() == position * 2 - 1 && course.getCurrentWeek() == currentWeek){
                        currentCourse.add(course);
                    }
                }
                for (int i = 0; i < currentCourse.size(); i ++){
                    SortCourse course = currentCourse.get(i);
                    String courseName = course.getCourse();
                    String classRoom = course.getClassroom();

                    if (course.getDay().equals(Weeks[0])){
                        ((TimeTableHolder) holder).monday.setText(getString(R.string.courseName_classRoom, courseName, classRoom));
                        ((TimeTableHolder) holder).monday.setBackgroundColor(getResources().getColor(Colors[position]));
                    } else if (course.getDay().equals(Weeks[1])){
                        ((TimeTableHolder) holder).tuesday.setText(getString(R.string.courseName_classRoom, courseName, classRoom));
                        ((TimeTableHolder) holder).tuesday.setBackgroundColor(getResources().getColor(Colors[position]));
                    } else if (course.getDay().equals(Weeks[2])){
                        ((TimeTableHolder) holder).wednesday.setText(getString(R.string.courseName_classRoom, courseName, classRoom));
                        ((TimeTableHolder) holder).wednesday.setBackgroundColor(getResources().getColor(Colors[position]));
                    } else if (course.getDay().equals(Weeks[3])){
                        ((TimeTableHolder) holder).thursday.setText(getString(R.string.courseName_classRoom, courseName, classRoom));
                        ((TimeTableHolder) holder).thursday.setBackgroundColor(getResources().getColor(Colors[position]));
                    } else if (course.getDay().equals(Weeks[4])){
                        ((TimeTableHolder) holder).friday.setText(getString(R.string.courseName_classRoom, courseName, classRoom));
                        ((TimeTableHolder) holder).friday.setBackgroundColor(getResources().getColor(Colors[position]));
                    } else if (course.getDay().equals(Weeks[5])){
                        ((TimeTableHolder) holder).saturday.setText(getString(R.string.courseName_classRoom, courseName, classRoom));
                        ((TimeTableHolder) holder).saturday.setBackgroundColor(getResources().getColor(Colors[position]));
                    } else if (course.getDay().equals(Weeks[6])){
                        ((TimeTableHolder) holder).sunday.setText(getString(R.string.courseName_classRoom, courseName, classRoom));
                        ((TimeTableHolder) holder).sunday.setBackgroundColor(getResources().getColor(Colors[position]));
                    }
                }
            }

            private void onBindWeekIndicatorHolder(RecyclerView.ViewHolder holder){
                ((WeekIndicatorHolder) holder).first.setText(WeeksPlus[0]);
                ((WeekIndicatorHolder) holder).second.setText(WeeksPlus[1]);
                ((WeekIndicatorHolder) holder).third.setText(WeeksPlus[2]);
                ((WeekIndicatorHolder) holder).forth.setText(WeeksPlus[3]);
                ((WeekIndicatorHolder) holder).fifth.setText(WeeksPlus[4]);
                ((WeekIndicatorHolder) holder).sixth.setText(WeeksPlus[5]);
                ((WeekIndicatorHolder) holder).seventh.setText(WeeksPlus[6]);
            }

            @Override
            public int getItemCount() {
                return mSortCourses == null? 0: 7;
            }

            @Override
            public int getItemViewType(int position) {
                if (position == 0){
                    return WEEKTYPE;
                } else {
                    return TABLETYPE;
                }
            }
        }

        private static class TimeTableHolder extends RecyclerView.ViewHolder{
            private TextView monday, tuesday, wednesday, thursday, friday, saturday, sunday, courseRank;
            private TimeTableHolder(View itemView) {
                super(itemView);
                monday = (TextView) itemView.findViewById(R.id.monday);
                tuesday = (TextView) itemView.findViewById(R.id.tuesday);
                thursday = (TextView) itemView.findViewById(R.id.thursday);
                wednesday = (TextView) itemView.findViewById(R.id.wednesday);
                friday = (TextView) itemView.findViewById(R.id.friday);
                saturday = (TextView) itemView.findViewById(R.id.saturday);
                sunday = (TextView) itemView.findViewById(R.id.sunday);
                courseRank = (TextView) itemView.findViewById(R.id.course_rank);
            }
        }

        private static class WeekIndicatorHolder extends RecyclerView.ViewHolder{
            private TextView first, second, third, forth, fifth, sixth, seventh;
            private WeekIndicatorHolder(View view){
                super(view);
                first = (TextView) view.findViewById(R.id.first);
                second = (TextView) view.findViewById(R.id.second);
                third = (TextView) view.findViewById(R.id.third);
                forth = (TextView) view.findViewById(R.id.fouth);
                fifth = (TextView) view.findViewById(R.id.fifth);
                sixth = (TextView) view.findViewById(R.id.sixth);
                seventh = (TextView) view.findViewById(R.id.seventh);
            }
        }
    }
}
