package com.cambodia.zhanbang.component.anno;


import com.cambodia.zhanbang.component.anno.support.MainThreadCreateAnno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记一个 Service 服务接口的方法是暴露的
 */
@MainThreadCreateAnno
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface ServiceMethodAnno {

    /**
     * 名称
     */
    String value();

}
