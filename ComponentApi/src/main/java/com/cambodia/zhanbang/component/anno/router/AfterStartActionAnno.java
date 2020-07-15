package com.cambodia.zhanbang.component.anno.router;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记一个 Action 是跳转前的 Action
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.CLASS)
public @interface AfterStartActionAnno {
}
