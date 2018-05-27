package com.example.weibinhuang.zhangshangchongyou_wb.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weibinhuang.zhangshangchongyou_wb.R;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.view.IaskQuestionView;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.presenter.IquestionPresenter;
import com.example.weibinhuang.zhangshangchongyou_wb.presenter.QuestionPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AskQuestionActivity extends AppCompatActivity implements IaskQuestionView, View.OnClickListener{

    private ImageView backView;
    private EditText titleView, questionView;
    private TextView nextView;
    private IquestionPresenter mIquestionPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        mIquestionPresenter = new QuestionPresenter();

        backView = (ImageView) findViewById(R.id.askquestion_back);
        nextView = (TextView) findViewById(R.id.askquestion_next);
        titleView = (EditText) findViewById(R.id.askquestion_edit_title);
        questionView = (EditText) findViewById(R.id.askquesstion_edit_question);
        nextView.setOnClickListener(this);
    }

    @Override
    public void upLoadQuestion() {
        String title = titleView.getText().toString();
        String description = questionView.getText().toString();
        String isAnonymous = "1";
        String kind = "情感";
        String reward = "1";
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String disappearTime = sdf.format(now);
        mIquestionPresenter.initAskQuestionView(this, title, description, isAnonymous, kind, reward, disappearTime);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.askquestion_next:
                upLoadQuestion();
                break;
        }
    }
}
