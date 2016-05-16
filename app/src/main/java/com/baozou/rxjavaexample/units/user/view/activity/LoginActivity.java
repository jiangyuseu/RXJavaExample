package com.baozou.rxjavaexample.units.user.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseActivity;
import com.baozou.rxjavaexample.units.user.presenter.ILoginPresenter;
import com.baozou.rxjavaexample.units.user.presenter.LoginPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2016/4/24.
 * 手机号注册登录界面
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.register_btn)
    public TextView registerBtn;

    @Bind(R.id.phone_number)
    public EditText phoneEdit;

    @Bind(R.id.password_edit)
    public EditText passEdit;

    private ILoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initActionbar();
        presenter = new LoginPresenter(this);
    }

    private void initActionbar() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.login_btn)
    public void login(){
        String phone = phoneEdit.getText().toString();
        String pwd = passEdit.getText().toString();
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "手机号不能为空!", Toast.LENGTH_SHORT).show();
        }else{
            if(TextUtils.isEmpty(pwd)){
                Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();
            }else{
                presenter.userLogin(phone,pwd);
            }
        }
    }
}
