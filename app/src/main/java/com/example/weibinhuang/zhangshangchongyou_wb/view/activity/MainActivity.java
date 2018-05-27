package com.example.weibinhuang.zhangshangchongyou_wb.view.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.weibinhuang.zhangshangchongyou_wb.R;
import com.example.weibinhuang.zhangshangchongyou_wb.view.frag.MainQuestionFragment;
import com.example.weibinhuang.zhangshangchongyou_wb.view.frag.MainTimeTableFragment;
import com.example.weibinhuang.zhangshangchongyou_wb.view.frag.TimeTableFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTimetable, mQuestion, mFind, mMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        initView();
    }

    private void initView(){
        mTimetable = findViewById(R.id.home_bottom_timetable);
        mQuestion = findViewById(R.id.home_bottom_question);
        mFind = findViewById(R.id.home_bottom_find);
        mMy = findViewById(R.id.home_bottom_my);
        mMy.setOnClickListener(this);
        mFind.setOnClickListener(this);
        mQuestion.setOnClickListener(this);
        mTimetable.setOnClickListener(this);
    }

    public void selected(){
        mTimetable.setSelected(false);
        mQuestion.setSelected(false);
        mFind.setSelected(false);
        mMy.setSelected(false);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment;
        switch (v.getId()){
            case R.id.home_bottom_timetable:
                fragment = new MainTimeTableFragment();
                selected();
                mTimetable.setSelected(true);
                ft.replace(R.id.fragment_container, fragment);
                ft.commit();
                break;
            case R.id.home_bottom_question:
                fragment = new MainQuestionFragment();
                selected();
                mQuestion.setSelected(true);
                ft.replace(R.id.fragment_container, fragment);
                ft.commit();
                break;
            case R.id.home_bottom_find:
                break;
            case R.id.home_bottom_my:
                break;
            default:
                break;
        }
    }
}
