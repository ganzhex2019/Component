package com.cambodia.zhanbang.component.support;


import androidx.annotation.NonNull;

import com.cambodia.zhanbang.component.anno.support.CheckClassNameAnno;

/**
 * 懒加载设计
 * time   : 2018/11/27

 */
@CheckClassNameAnno
public interface Callable<T> {

    /**
     * 获取实际的兑现
     *
     * @return 获取实现对象
     */
    @NonNull
    T get();

}
