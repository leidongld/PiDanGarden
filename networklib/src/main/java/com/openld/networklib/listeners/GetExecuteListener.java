package com.openld.networklib.listeners;

/**
 * author: lllddd
 * created on: 2021/8/24 10:50
 * description:GET同步请求返回结果监听器
 */
public interface GetExecuteListener<T> {
    /**
     * 返回结果回调
     *
     * @param response 返回结果
     */
    void onResponse(T response);
}
