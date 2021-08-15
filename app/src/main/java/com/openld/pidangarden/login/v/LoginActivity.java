package com.openld.pidangarden.login.v;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.openld.pidangarden.R;
import com.openld.pidangarden.login.LoginContract;
import com.openld.pidangarden.login.beans.LoginInfoBean;
import com.openld.pidangarden.login.p.LoginPresenter;
import com.openld.pidangarden.main.v.MainActivity;
import com.openld.toolkit.PreventContinueClickListener;
import com.openld.viewbox.RoundImageView;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements LoginContract.ILoginView {
    private static final String TAG = "LoginActivity";

    private LoginContract.ILoginPresenter mPresenter;

    private RoundImageView mRoundImgPiDanAvatar;

    private AppCompatEditText mEdtUsername;

    private AppCompatEditText mEdtPassword;

    private AppCompatButton mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initWidgets();

        mPresenter = new LoginPresenter();
        mPresenter.bindPresenter(this);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0F, 1F, 0F, 1F, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mRoundImgPiDanAvatar.setAnimation(scaleAnimation);
        scaleAnimation.start();
    }

    private void initWidgets() {
        mRoundImgPiDanAvatar = findViewById(R.id.rimg_pidan_avatar);
        mEdtUsername = findViewById(R.id.edt_username);
        mEdtPassword = findViewById(R.id.edt_password);
        mBtnLogin = findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(new PreventContinueClickListener() {
            @Override
            public void onClick(View v) {
                String username = Objects.requireNonNull(mEdtUsername.getText()).toString();
                String password = Objects.requireNonNull(mEdtPassword.getText()).toString();
                LoginInfoBean loginInfoBean = new LoginInfoBean();
                loginInfoBean.setUsername(username);
                loginInfoBean.setPassword(password);
                mPresenter.login(loginInfoBean);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.releasePresenter();
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFail() {
        Toast.makeText(this, "登录失败！！！", Toast.LENGTH_SHORT).show();
    }
}