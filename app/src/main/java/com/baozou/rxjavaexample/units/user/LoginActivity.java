package com.baozou.rxjavaexample.units.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2016/4/24.
 * 手机号注册登录界面
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.register_btn)
    public TextView registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initActionbar();
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
}
