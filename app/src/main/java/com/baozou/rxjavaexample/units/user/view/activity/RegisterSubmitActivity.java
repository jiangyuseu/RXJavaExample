package com.baozou.rxjavaexample.units.user.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseActivity;
import com.baozou.rxjavaexample.model.UserBean;
import com.baozou.rxjavaexample.units.user.presenter.IRegisterPresenter;
import com.baozou.rxjavaexample.units.user.presenter.RegisterPresenter;
import com.baozou.rxjavaexample.units.user.view.RegisterView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by lenovo on 2016/5/15.
 * 提交注册用户信息
 */
public class RegisterSubmitActivity extends BaseActivity implements RegisterView{

    public static String PHONE_KEY;

    @Bind(R.id.actionbar_title)
    public TextView actionbarTitle;

    @Bind(R.id.pass_edit_1)
    public EditText passEdit1;

    @Bind(R.id.pass_edit)
    public EditText passEdit;

    private String phone;

    private IRegisterPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_submit);
        ButterKnife.bind(this);
        initActionBar();
        presenter = new RegisterPresenter(this,this);
//        phone = getIntent().getStringExtra(PHONE_KEY);
        phone = "15151812411";
    }

    @Override
    public void showUserInfo(UserBean bean) {

    }

    private void initActionBar(){
        actionbarTitle.setText("注册");
    }

    @OnClick(R.id.actionbar_back)
    public void back(){
        this.finish();
    }

    @OnClick(R.id.submit_btn)
    public void submitUserInfo(){
        String pass1 = passEdit1.getText().toString();
        String pass2 = passEdit1.getText().toString();
        if(TextUtils.isEmpty(pass1) || TextUtils.isEmpty(pass2)){
            Toast.makeText(RegisterSubmitActivity.this,"请输入密码!",Toast.LENGTH_SHORT).show();
        }else{
            if(!pass1.equals(pass2)){
                Toast.makeText(RegisterSubmitActivity.this,"两次的密码不一致!",Toast.LENGTH_SHORT).show();
            }else{
                //提交手机号与密码
                presenter.userRegist(phone,pass1);
            }
        }
    }
}