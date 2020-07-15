package com.cambodia.zhanbang.component.anno;



import com.cambodia.zhanbang.component.anno.support.MainThreadCreateAnno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记一个 Fragment, 方便路由到此注解标记的 Fragment
 */
@MainThreadCreateAnno
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface FragmentAnno {

    /**
     * 这个 Fragment 对应的唯一 ID
     *
     * @return 对应 Fragment 的一个标记, 不能重复
     */
    String[] value();

}
