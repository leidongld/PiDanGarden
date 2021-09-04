package com.openld.networklib.listeners;

/**
 * author: lllddd
 * created on: 2021/8/24 10:53
 * description:GET异步请求返回结果监听器
 */
public interface GetEnqueueListener<T> {
    /**
     * 返回结果回调
     *
     * @param response 返回结果
     */
    void onResponse(T response);

    /**
     * 失败回调
     *
     * @param message 异常信息
     */
    void onFailure(String message);
}
