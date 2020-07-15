package com.cambodia.zhanbang.component.support;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.cambodia.zhanbang.component.anno.RouterAnno;
import com.cambodia.zhanbang.component.anno.support.CheckClassNameAnno;
import com.cambodia.zhanbang.component.impl.RouterRequest;


/**
 * 跳转的 Intent 用户自定义,{@link RouterAnno} 注解标记在静态方法上的时候
 * 当返回值是 {@link Intent} 的时候,就会产生一个此接口的实现,实现获取目标界面跳转的 {@link Intent}
 */
@CheckClassNameAnno
public interface CustomerIntentCall {

    /**
     * 获取创建的 Intent
     *
     * @param request 路由请求对象
     * @return
     */
    @NonNull
    Intent get(@NonNull RouterRequest request) throws Exception;

}