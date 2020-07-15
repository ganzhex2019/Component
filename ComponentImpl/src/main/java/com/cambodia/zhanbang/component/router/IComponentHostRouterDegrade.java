package com.cambodia.zhanbang.component.router;



import androidx.annotation.NonNull;

import com.cambodia.zhanbang.component.bean.RouterDegradeBean;
import com.cambodia.zhanbang.component.support.IHost;

import java.util.List;

/**
 * 模块的路由的降级处理
 * <p>
 * time   : 2018/07/26
 *
 */
public interface IComponentHostRouterDegrade extends IHost {

    /**
     * 获取这个模块的降级处理
     *
     * @throws Exception
     */
    @NonNull
    List<RouterDegradeBean> listRouterDegrade();

}
