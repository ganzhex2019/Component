package com.cambodia.zhanbang.component.support;


import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

/**
 * 获取 Host 的接口
 */
public interface IHost {

    /**
     * 获取模块的 host
     */
    @NonNull
    @MainThread
    String getHost();

}
