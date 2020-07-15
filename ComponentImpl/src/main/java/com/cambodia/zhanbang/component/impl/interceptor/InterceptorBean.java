package com.cambodia.zhanbang.component.impl.interceptor;


import com.cambodia.zhanbang.component.impl.RouterInterceptor;

/**
 * time   : 2018/12/26

 */
public class InterceptorBean {

    /**
     * 拦截器
     */
    public final RouterInterceptor interceptor;

    /**
     * 优先级
     */
    public final int priority;

    public InterceptorBean(RouterInterceptor interceptor, int priority) {
        this.interceptor = interceptor;
        this.priority = priority;
    }

}
