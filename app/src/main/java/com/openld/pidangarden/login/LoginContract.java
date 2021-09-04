package com.openld.pidangarden.login;

import androidx.annotation.NonNull;

import com.openld.pidangarden.base.BasePresenter;
import com.openld.pidangarden.base.BaseView;
import com.openld.pidangarden.login.beans.LoginInfoBean;

/**
 * author: lllddd
 * created on: 2021/7/26 16:21
 * description:
 */
public class LoginContract {
    public interface ILoginView extends BaseView {
        /**
         * 登录成功
         */
        void loginSuccess();

        /**
         * 登录失败
         */
        void loginFail();
    }

    public interface ILoginPresenter extends BasePresenter {

        /**
         * 执行登录
         *
         * @param loginInfoBean 登录信息对象
         */
        void login(@NonNull LoginInfoBean loginInfoBean);

        /**
         * 跳转到主页
         */
        void jumpToMainPage();
    }
}
