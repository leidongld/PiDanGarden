package com.openld.pidangarden.splash.v;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.openld.pidangarden.R;
import com.openld.pidangarden.login.v.LoginActivity;
import com.openld.pidangarden.splash.SplashContract;
import com.openld.pidangarden.splash.p.SplashPresenter;
import com.openld.toolkit.LogUtils;
import com.openld.toolkit.PreventContinueClickListener;

public class SplashActivity extends AppCompatActivity implements SplashContract.ISplashView {
    private static final String TAG = "SplashActivity";

    // 动画持续的时间2s
    private static final long DURATION = 2000L;

    // 最外层布局容器
    private ConstraintLayout mClyContainer;

    // 进入按钮
    private AppCompatButton mBtnEnter;

    private SplashContract.ISplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mPresenter = new SplashPresenter();
        mPresenter.bindPresenter(this);

        initWidgets();

        changeAlpha();
    }

    /**
     * 更改透明度
     */
    private void changeAlpha() {
        LogUtils.d(TAG, "改变透明度");
        AlphaAnimation animation = new AlphaAnimation(0F, 1F);
        animation.setDuration(DURATION);
        mClyContainer.setAnimation(animation);
        animation.start();
    }

    /**
     * 初始化控件
     */
    private void initWidgets() {
        mClyContainer = findViewById(R.id.cly_container);
        mBtnEnter = findViewById(R.id.btn_enter);

        mBtnEnter.setOnClickListener(new PreventContinueClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.releasePresenter();
    }
}