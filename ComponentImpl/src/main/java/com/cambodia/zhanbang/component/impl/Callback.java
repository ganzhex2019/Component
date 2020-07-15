package com.cambodia.zhanbang.component.impl;



import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cambodia.zhanbang.component.anno.support.CheckClassNameAnno;
import com.cambodia.zhanbang.component.support.OnRouterCancel;
import com.cambodia.zhanbang.component.support.OnRouterError;

/**
 * 当路由完成的时候,回调这个接口,这时候的完成不一定是成功的,可能是失败的,成功和失败都表示完成
 * 所有的调用顺序整理：
 * 1.{@link #onSuccess(RouterResult)} --> {@link #onEvent(RouterResult, RouterErrorResult)}
 * 2.{@link #onError(RouterErrorResult)} --> {@link #onEvent(RouterResult, RouterErrorResult)}
 * 3.被取消的时候：{@link #onCancel(RouterRequest)}
 *
 */
@CheckClassNameAnno
public interface Callback extends OnRouterError, OnRouterCancel {

    /**
     * 当路由成功(这里泛指两点：)的时候,回调
     * 这里的路由成功不等同于跳转成功.指的是下面两点：
     * 1. 普通跳转的跳转成功
     * 2. 为了匹配 {@link com.cambodia.zhanbang.component.bean.ActivityResult#resultCode} 匹配成功
     *
     * @param result 路由成功的对象
     * @see Navigator#navigate()
     * @see Navigator#navigate(Callback)
     * @see Navigator#navigateForResultCodeMatch(Callback, int)
     */
    @MainThread
    void onSuccess(@NonNull RouterResult result);

    /**
     * 两个参数肯定有一个不会为空
     *
     * @param successResult 路由成功时候的返回对象,成功的对象
     * @param errorResult   发生的错误对象
     */
    @MainThread
    void onEvent(@Nullable RouterResult successResult, @Nullable RouterErrorResult errorResult);

}
