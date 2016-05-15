package com.baozou.rxjavaexample.units.user.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baozou.rxjavaexample.R;
import com.baozou.rxjavaexample.base.BaseActivity;
import com.baozou.rxjavaexample.common.Constants;
import com.baozou.rxjavaexample.utils.PhoneUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by jiangyu on 2016/4/26.
 * 验证手机号合法性
 */
public class RegisterActivity extends BaseActivity {

    @Bind(R.id.actionbar_back)
    public ImageView back;

    @Bind(R.id.actionbar_title)
    public TextView actionbarTitle;

    @Bind(R.id.phone_number)
    public EditText phoneNumber;

    @Bind(R.id.get_verify)
    public TextView getVerify;

    @Bind(R.id.time_end)
    public TextView endTimeTxt;

    @Bind(R.id.submit_btn)
    public TextView submit;

    @Bind(R.id.code_edit)
    public EditText codeTxt;

    private int endTime = 60;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initSMSSdk();
        initActionbar();
        initView();
    }

    private void initActionbar() {
        actionbarTitle.setText("注册");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
    }

    private void initView() {
        getVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(phoneNumber.getText().toString())) {
                    if (PhoneUtils.isMobileNO(phoneNumber.getText().toString())) {
                        SMSSDK.getVerificationCode("86", phoneNumber.getText().toString());
                        handler.postDelayed(timerRunnable, 1000);
                    } else {
                        Toast.makeText(RegisterActivity.this, "请输入合法的手机号", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = codeTxt.getText().toString();
                String phone = phoneNumber.getText().toString();
                if (!TextUtils.isEmpty(code) && !TextUtils.isEmpty(phone)) {
                    SMSSDK.submitVerificationCode("86", phone, code);
                }
            }
        });
    }


    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if (endTime > 0) {
                endTime--;
                endTimeTxt.setVisibility(View.VISIBLE);
                getVerify.setVisibility(View.GONE);
                endTimeTxt.setText("" + endTime);
                handler.postDelayed(timerRunnable, 1000);
            } else {
                endTimeTxt.setVisibility(View.GONE);
                getVerify.setVisibility(View.VISIBLE);
                endTime = 60;
            }
        }
    };

    private void initSMSSdk() {
        SMSSDK.initSDK(this, Constants.SMSSDK_KEY, Constants.SMSSDK_Secret);
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        Intent intent = new Intent(RegisterActivity.this,RegisterSubmitActivity.class);
                        intent.putExtra(RegisterSubmitActivity.PHONE_KEY,phoneNumber.getText().toString());
                        startActivity(intent);
                        //提交验证码成功
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }
}
