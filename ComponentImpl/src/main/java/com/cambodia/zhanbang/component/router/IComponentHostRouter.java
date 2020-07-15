package com.cambodia.zhanbang.component.router;


import androidx.annotation.NonNull;

import com.cambodia.zhanbang.component.bean.RouterBean;
import com.cambodia.zhanbang.component.support.IHost;

import java.util.Map;

/**
 * 组件之间实现跳转的接口
 * <p>
 * time   : 2018/07/26
 */
public interface IComponentHostRouter extends IHost {

    /**
     * 获取路由表,用于检查
     */
    @NonNull
    Map<String, RouterBean> getRouterMap();

}
