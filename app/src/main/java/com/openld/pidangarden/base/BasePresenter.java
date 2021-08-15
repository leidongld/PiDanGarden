package com.openld.pidangarden.base;

/**
 * author: lllddd
 * created on: 2021/7/26 16:26
 * description:
 */
public interface BasePresenter<T extends BaseView> {
    void releasePresenter();

    void bindPresenter(T view);
}
