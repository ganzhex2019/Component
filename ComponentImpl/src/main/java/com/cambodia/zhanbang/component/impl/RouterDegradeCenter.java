package com.cambodia.zhanbang.component.impl;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cambodia.zhanbang.component.Component;
import com.cambodia.zhanbang.component.ComponentUtil;
import com.cambodia.zhanbang.component.anno.support.CheckClassNameAnno;
import com.cambodia.zhanbang.component.bean.RouterDegradeBean;
import com.cambodia.zhanbang.component.router.IComponentCenterRouterDegrade;
import com.cambodia.zhanbang.component.router.IComponentHostRouterDegrade;
import com.cambodia.zhanbang.component.support.ASMUtil;
import com.cambodia.zhanbang.component.support.RouterDegradeCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CheckClassNameAnno
public class RouterDegradeCenter implements IComponentCenterRouterDegrade {

    private RouterDegradeCenter() {
    }

    /**
     * 单例对象
     */
    private static volatile RouterDegradeCenter instance;

    /**
     * 获取单例对象
     *
     * @return
     */
    public static RouterDegradeCenter getInstance() {
        if (instance == null) {
            synchronized (RouterDegradeCenter.class) {
                if (instance == null) {
                    instance = new RouterDegradeCenter();
                }
            }
        }
        return instance;
    }

    /**
     * 子拦截器对象管理 map
     */
    private Map<String, IComponentHostRouterDegrade> moduleRouterDegradeMap = new HashMap<>();

    /**
     * 全局的降级处理, 数据一定是排序过的
     */
    private List<RouterDegradeItem> mGlobalRouterDegradeList = new ArrayList<>();

    /**
     * 是否降级处理列表发生变化
     */
    private boolean isRouterDegradeListHaveChange = false;

    @NonNull
    public List<RouterDegrade> getGlobalRouterDegradeList() {
        if (isRouterDegradeListHaveChange) {
            loadAllGlobalRouterDegrade();
            isRouterDegradeListHaveChange = false;
        }
        List<RouterDegrade> result = new ArrayList<>();
        for (RouterDegradeItem item : mGlobalRouterDegradeList) {
            result.add(item.routerDegrade);
        }
        return result;
    }

    /**
     * 按顺序弄好所有全局拦截器
     */
    private void loadAllGlobalRouterDegrade() {
        mGlobalRouterDegradeList.clear();
        List<RouterDegradeBean> totalList = new ArrayList<>();
        // 加载各个子拦截器对象中的拦截器列表
        for (Map.Entry<String, IComponentHostRouterDegrade> entry : moduleRouterDegradeMap.entrySet()) {
            List<RouterDegradeBean> list = entry.getValue().listRouterDegrade();
            totalList.addAll(list);
        }
        for (RouterDegradeBean routerDegradeBean : totalList) {
            mGlobalRouterDegradeList.add(
                    new RouterDegradeItem(
                            routerDegradeBean.getPriority(),
                            RouterDegradeCache.getRouterDegradeByClass(routerDegradeBean.getTargetClass())
                    ));
        }
        // 排序所有的拦截器对象,按照优先级排序
        Collections.sort(mGlobalRouterDegradeList, new Comparator<RouterDegradeItem>() {
            @Override
            public int compare(RouterDegradeItem o1, RouterDegradeItem o2) {
                if (o1.priority == o2.priority) {
                    return 0;
                } else if (o1.priority > o2.priority) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

    }

    @Override
    public void register(@Nullable IComponentHostRouterDegrade routerDegrade) {
        if (routerDegrade == null) {
            return;
        }
        isRouterDegradeListHaveChange = true;
        moduleRouterDegradeMap.put(routerDegrade.getHost(), routerDegrade);
    }

    @Override
    public void register(@NonNull String host) {
        if (moduleRouterDegradeMap.containsKey(host)) {
            return;
        }
        IComponentHostRouterDegrade moduleRouterDegrade = findModuleRouterDegrade(host);
        register(moduleRouterDegrade);
    }

    @Override
    public void unregister(@Nullable IComponentHostRouterDegrade routerDegrade) {
        if (routerDegrade == null) {
            return;
        }
        moduleRouterDegradeMap.remove(routerDegrade.getHost());
        isRouterDegradeListHaveChange = true;
    }

    @Override
    public void unregister(@NonNull String host) {
        IComponentHostRouterDegrade moduleRouterDegrade = moduleRouterDegradeMap.get(host);
        unregister(moduleRouterDegrade);
    }

    @Nullable
    public IComponentHostRouterDegrade findModuleRouterDegrade(String host) {
        try {
            if (Component.getConfig().isOptimizeInit()) {
                return ASMUtil.findModuleRouterDegradeAsmImpl(host);
            }else {
                Class<? extends IComponentHostRouterDegrade> clazz = null;
                String className = ComponentUtil.genHostRouterDegradeClassName(host);
                clazz = (Class<? extends IComponentHostRouterDegrade>) Class.forName(className);
                return clazz.newInstance();
            }
        } catch (Exception ignore) {
            // ignore
        }
        return null;
    }

    private class RouterDegradeItem {

        /**
         * 优先级
         */
        public int priority;

        @NonNull
        public RouterDegrade routerDegrade;

        public RouterDegradeItem(int priority, @NonNull RouterDegrade routerDegrade) {
            this.priority = priority;
            this.routerDegrade = routerDegrade;
        }

    }

}
