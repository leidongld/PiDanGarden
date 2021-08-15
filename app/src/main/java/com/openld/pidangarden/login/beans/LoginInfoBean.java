package com.openld.pidangarden.login.beans;

import java.io.Serializable;

/**
 * author: lllddd
 * created on: 2021/8/15 10:30
 * description:登录信息Bean
 */
public class LoginInfoBean implements Serializable {
    // 用户名
    private String username;

    // 密码
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
