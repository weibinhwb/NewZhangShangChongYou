package com.example.weibinhuang.zhangshangchongyou_wb.view.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weibinhuang.zhangshangchongyou_wb.R;
import com.example.weibinhuang.zhangshangchongyou_wb.bean.TimeTable;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.presenter.ItimetablePresenter;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.view.ITimeTableView;
import com.example.weibinhuang.zhangshangchongyou_wb.presenter.TimeTablePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by weibinhuang on 18-5-27.
 */

public class TimeTableFragment extends Fragment implements ITimeTableView{

    private RecyclerView mRecyclerView;
    private TimeTableRecycleAdapter mTimeTableRecycleAdapter;
    private List<TimeTable.TimeTableDetail> mTimeTableDetailList;
    private List<TimeTable.TimeTableDetail> mListMonday = new ArrayList<>();
    private List<TimeTable.TimeTableDetail> mListTuesday = new ArrayList<>();
    private List<TimeTable.TimeTableDetail> mListWednesday = new ArrayList<>();
    private List<TimeTable.TimeTableDetail> mListThursday = new ArrayList<>();
    private List<TimeTable.TimeTableDetail> mListFriday = new ArrayList<>();
    private List<TimeTable.TimeTableDetail> mListSaturday = new ArrayList<>();
    private List<TimeTable.TimeTableDetail> mListSunday = new ArrayList<>();
    private ItimetablePresenter mItimetablePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItimetablePresenter = new TimeTablePresenter();
        mItimetablePresenter.initTimeTableView(this);
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
    public void showTimeTable(List<TimeTable.TimeTableDetail> list) {
        mTimeTableDetailList = list;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTimeTableRecycleAdapter.notifyDataSetChanged();
            }
        });
        initData();
    }

    public void initData(){
        for (int i = 0; i < mTimeTableDetailList.size(); i ++){
            String day = mTimeTableDetailList.get(i).getDay();
            switch (day){
                case "星期一":
                    mListMonday.add(mTimeTableDetailList.get(i));
                    break;
                case "星期二":
                    mListTuesday.add(mTimeTableDetailList.get(i));
                    break;
                case "星期三":
                    mListWednesday.add(mTimeTableDetailList.get(i));
                    break;
                case "星期四":
                    mListThursday.add(mTimeTableDetailList.get(i));
                    break;
                case "星期五":
                    mListFriday.add(mTimeTableDetailList.get(i));
                    break;
                case "星期六":
                    mListSaturday.add(mTimeTableDetailList.get(i));
                    break;
                case "星期日":
                    mListSunday.add(mTimeTableDetailList.get(i));
                    break;
                default:
                    break;
            }
        }
    }

    class TimeTableRecycleAdapter extends RecyclerView.Adapter{

        private static final int SIZE = 6;
        private int a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0, a6 = 0, a7 = 0;
        private static final int WEEKTYPE = 0;
        private static final int TABLETYPE = 1;

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

                if (mListMonday.size() > position && mListMonday.get(position).getDay().equals("星期一"))
                    ((TimeTableHolder) holder).monday.setText(mListMonday.get(a1 ++).getCourse());

                if (mListTuesday.size() > position && mListTuesday.get(position).getDay().equals("星期二"))
                    ((TimeTableHolder) holder).tuesday.setText(mListTuesday.get(a2 ++).getCourse());

                if (mListWednesday.size() > position && mListWednesday.get(position).getDay().equals("星期三"))
                    ((TimeTableHolder) holder).wednesday.setText(mListWednesday.get(a3 ++).getCourse());

                if (mListThursday.size() > position && mListThursday.get(position).getDay().equals("星期四"))
                    ((TimeTableHolder) holder).thursday.setText(mListThursday.get(a4 ++).getCourse());

                if (mListFriday.size() > position && mListFriday.get(position).getDay().equals("星期五"))
                    ((TimeTableHolder) holder).friday.setText(mListFriday.get(a5 ++).getCourse());

                if (mListSaturday.size() > position && mListSaturday.get(position).getDay().equals("星期六"))
                    ((TimeTableHolder) holder).saturday.setText(mListSaturday.get(a6 ++).getCourse());

                if (mListSunday.size() > position && mListSunday.get(position).getDay().equals("星期日"))
                    ((TimeTableHolder) holder).sunday.setText(mListSunday.get(a7 ++).getCourse());
            }
            else if (holder instanceof WeekIndicatorHolder){

                ((WeekIndicatorHolder) holder).first.setText("星期一");
                ((WeekIndicatorHolder) holder).second.setText("星期二");
                ((WeekIndicatorHolder) holder).third.setText("星期三");
                ((WeekIndicatorHolder) holder).forth.setText("星期四");
                ((WeekIndicatorHolder) holder).fifth.setText("星期五");
                ((WeekIndicatorHolder) holder).sixth.setText("星期六");
                ((WeekIndicatorHolder) holder).seventh.setText("星期日");
            }
        }

        @Override
        public int getItemCount() {
            return mTimeTableDetailList == null ? 0 : SIZE;
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
        private TextView monday, tuesday, wednesday, thursday, friday, saturday, sunday;
        private TimeTableHolder(View itemView) {
            super(itemView);
            monday = (TextView) itemView.findViewById(R.id.monday);
            tuesday = (TextView) itemView.findViewById(R.id.tuesday);
            thursday = (TextView) itemView.findViewById(R.id.thursday);
            wednesday = (TextView) itemView.findViewById(R.id.wednesday);
            friday = (TextView) itemView.findViewById(R.id.friday);
            saturday = (TextView) itemView.findViewById(R.id.saturday);
            sunday = (TextView) itemView.findViewById(R.id.sunday);
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
