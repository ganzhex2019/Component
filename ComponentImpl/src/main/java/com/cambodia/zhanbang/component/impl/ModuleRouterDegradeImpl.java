package com.cambodia.zhanbang.component.impl;



import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import com.cambodia.zhanbang.component.bean.RouterDegradeBean;
import com.cambodia.zhanbang.component.router.IComponentHostRouterDegrade;

import java.util.ArrayList;
import java.util.List;

public abstract class ModuleRouterDegradeImpl implements IComponentHostRouterDegrade {

    /**
     * 降级处理的类
     */
    protected final List<RouterDegradeBean> routerDegradeBeanList = new ArrayList<>();

    /**
     * 是否初始化了map,懒加载
     */
    protected boolean hasInitList = false;

    @CallSuper
    protected void initList() {
        hasInitList = true;
    }

    @NonNull
    @Override
    public List<RouterDegradeBean> listRouterDegrade() {
        if (!hasInitList) {
            initList();
        }
        return new ArrayList<>(routerDegradeBeanList);
    }

}
