package com.example.weibinhuang.zhangshangchongyou_wb.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weibinhuang.zhangshangchongyou_wb.R;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.view.IloginView;
import com.example.weibinhuang.zhangshangchongyou_wb.imp.presenter.IuserBreifPresenter;
import com.example.weibinhuang.zhangshangchongyou_wb.presenter.UserBriefPresenter;

public class LoginActivity extends AppCompatActivity implements IloginView, View.OnClickListener{


    private EditText mAccount, mPassWord;
    private IuserBreifPresenter mIuserBreifPresenter;
    private Button mLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mIuserBreifPresenter = new UserBriefPresenter();
        mAccount = (EditText) findViewById(R.id.login_account);
        mPassWord = (EditText) findViewById(R.id.login_password);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void updateView() {
        String account = mAccount.getText().toString();
        String password = mPassWord.getText().toString();
        mIuserBreifPresenter.login(this, account, password, this);
    }

    @Override
    public void updateViewSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateViewFailed() {
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               Toast.makeText(LoginActivity.this,"登录失败", Toast.LENGTH_SHORT).show();
           }
       });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                updateView();
                break;
            default:
                break;
        }
    }
}
