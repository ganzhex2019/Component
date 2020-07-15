package com.cambodia.zhanbang.component.support;


import androidx.annotation.NonNull;

import com.cambodia.zhanbang.component.anno.support.CheckClassNameAnno;

/**
 * 表示一个接受一个参数的接口
 */
@CheckClassNameAnno
public interface Consumer<T> {

    /**
     * 接受一个参数的方法,允许抛出异常
     *
     * @param t 方法的参数值
     * @throws Exception 如果发生异常的时候允许抛出异常
     */
    void accept(@NonNull T t) throws Exception;

}