package com.cambodia.zhanbang.component.support;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cambodia.zhanbang.component.anno.support.CheckClassNameAnno;


/**
 * 每一个生成的类都应该实现这个接口
 */
@CheckClassNameAnno
public interface Inject<T> {

    /**
     * 注入属性值, Bundle 从 {@link Activity#getIntent()} 和 {@link Fragment#getArguments()} 中来
     *
     * @param target 目标
     */
    @MainThread
    void injectAttrValue(@NonNull T target);

    /**
     * 注入属性值
     *
     * @param target 目标
     * @param bundle 数据源的 bundle
     */
    @MainThread
    void injectAttrValue(@NonNull T target, @NonNull Bundle bundle);

    /**
     * 注入 Service
     *
     * @param target 目标
     */
    @MainThread
    void injectService(@NonNull T target);

}
