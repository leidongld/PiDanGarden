package com.openld.networklib.listeners;

/**
 * author: lllddd
 * created on: 2021/8/24 11:12
 * description:POST同步请求返回结果监听器
 */
public interface PostExecuteListener<T> {
    /**
     * 返回结果回调
     *
     * @param response 返回结果
     */
    void onResponse(T response);
}
