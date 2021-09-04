package com.openld.pidangarden.login.p;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.openld.pidangarden.base.BaseView;
import com.openld.pidangarden.login.LoginContract;
import com.openld.pidangarden.login.beans.LoginInfoBean;
import com.openld.pidangarden.main.v.MainActivity;

/**
 * author: lllddd
 * created on: 2021/7/26 16:23
 * description:登录页面的Presenter
 */
public class LoginPresenter implements LoginContract.ILoginPresenter {
    private LoginContract.ILoginView mView;

    public LoginPresenter() {
    }

    @Override
    public void releasePresenter() {
        mView = null;
    }

    @Override
    public void bindPresenter(BaseView view) {
        this.mView = (LoginContract.ILoginView) view;
    }

    @Override
    public void login(@Nullable LoginInfoBean loginInfoBean) {
        if ("lllddd".equals(loginInfoBean.getUsername()) && "123456".equals(loginInfoBean.getPassword())) {
            mView.loginSuccess();
        } else {
            mView.loginFail();
        }
    }

    @Override
    public void jumpToMainPage() {
        Intent intent = new Intent((Context) mView, MainActivity.class);
        ((Activity) mView).startActivity(intent);
        ((Activity) mView).finish();
    }
}
