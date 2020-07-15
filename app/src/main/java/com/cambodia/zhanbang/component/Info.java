package com.cambodia.zhanbang.component;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Info {
    int id() default 0;
    String name() default "zhang";
    String password() default "123";
}
