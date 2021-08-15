package com.openld.pidangarden.login.p;

import androidx.annotation.Nullable;

import com.openld.pidangarden.base.BaseView;
import com.openld.pidangarden.login.LoginContract;
import com.openld.pidangarden.login.beans.LoginInfoBean;

/**
 * author: lllddd
 * created on: 2021/7/26 16:23
 * description:
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
}
