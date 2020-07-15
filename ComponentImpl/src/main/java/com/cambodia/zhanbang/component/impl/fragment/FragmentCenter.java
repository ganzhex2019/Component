package com.cambodia.zhanbang.component.impl.fragment;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cambodia.zhanbang.component.Component;
import com.cambodia.zhanbang.component.ComponentUtil;
import com.cambodia.zhanbang.component.anno.support.CheckClassNameAnno;
import com.cambodia.zhanbang.component.fragment.IComponentCenterFragment;
import com.cambodia.zhanbang.component.fragment.IComponentHostFragment;
import com.cambodia.zhanbang.component.support.ASMUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 模块 Fragment 注册和卸载的总管
 *
 */
@CheckClassNameAnno
public class FragmentCenter implements IComponentCenterFragment {

    private Map<String, IComponentHostFragment> moduleFragmentMap = new HashMap<>();

    private FragmentCenter() {
    }

    private static volatile FragmentCenter instance;

    public static FragmentCenter getInstance() {
        if (instance == null) {
            synchronized (FragmentCenter.class) {
                if (instance == null) {
                    instance = new FragmentCenter();
                }
            }
        }
        return instance;
    }

    @Override
    public void register(@Nullable IComponentHostFragment service) {
        if (service == null) {
            return;
        }
        moduleFragmentMap.put(service.getHost(), service);
        service.onCreate(Component.getApplication());
    }

    @Override
    public void register(@NonNull String host) {
        if (moduleFragmentMap.containsKey(host)) {
            return;
        }
        IComponentHostFragment moduleService = findModuleService(host);
        register(moduleService);
    }

    @Override
    public void unregister(@Nullable IComponentHostFragment moduleService) {
        if (moduleService == null) {
            return;
        }
        moduleFragmentMap.remove(moduleService.getHost());
        moduleService.onDestroy();
    }

    @Override
    public void unregister(@NonNull String host) {
        IComponentHostFragment moduleService = moduleFragmentMap.get(host);
        unregister(moduleService);
    }

    @Nullable
    public IComponentHostFragment findModuleService(String host) {
        try {
            if (Component.getConfig().isOptimizeInit()) {
                return ASMUtil.findModuleFragmentAsmImpl(host);
            } else {
                Class<? extends IComponentHostFragment> clazz = null;
                String className = ComponentUtil.genHostFragmentClassName(host);
                clazz = (Class<? extends IComponentHostFragment>) Class.forName(className);
                return clazz.newInstance();
            }
        } catch (Exception ignore) {
            // ignore
        }
        return null;
    }

    public void check() {
        Set<String> set = new HashSet<>();
        for (Map.Entry<String, IComponentHostFragment> entry : moduleFragmentMap.entrySet()) {
            IComponentHostFragment childRouter = entry.getValue();
            if (childRouter == null || childRouter.getFragmentNameSet() == null) {
                continue;
            }
            Set<String> childRouterSet = childRouter.getFragmentNameSet();
            for (String key : childRouterSet) {
                if (set.contains(key)) {
                    throw new IllegalStateException("the name of Fragment is exist：'" + key + "'");
                }
                set.add(key);
            }
        }
    }

}