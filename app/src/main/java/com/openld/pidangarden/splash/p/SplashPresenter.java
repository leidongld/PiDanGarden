package com.openld.pidangarden.splash.p;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.openld.pidangarden.base.BaseView;
import com.openld.pidangarden.login.v.LoginActivity;
import com.openld.pidangarden.splash.SplashContract;

/**
 * author: lllddd
 * created on: 2021/8/17 16:45
 * description:欢迎页的Presenter
 */
public class SplashPresenter implements SplashContract.ISplashPresenter {
    private SplashContract.ISplashView mView;

    public SplashPresenter() {
    }

    @Override
    public void releasePresenter() {
        this.mView = null;
    }

    @Override
    public void bindPresenter(BaseView view) {
        this.mView = (SplashContract.ISplashView) view;
    }

    @Override
    public void jumpToLoginPage() {
        Intent intent = new Intent((Context) mView, LoginActivity.class);
        ((Activity) mView).startActivity(intent);
        ((Activity) mView).finish();
    }
}
