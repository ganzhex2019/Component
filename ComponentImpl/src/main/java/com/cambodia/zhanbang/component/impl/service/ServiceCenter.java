package com.cambodia.zhanbang.component.impl.service;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cambodia.zhanbang.component.Component;
import com.cambodia.zhanbang.component.ComponentUtil;
import com.cambodia.zhanbang.component.anno.support.CheckClassNameAnno;
import com.cambodia.zhanbang.component.service.IComponentCenterService;
import com.cambodia.zhanbang.component.service.IComponentHostService;
import com.cambodia.zhanbang.component.support.ASMUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 模块服务注册和卸载的总管
 *
 */
@CheckClassNameAnno
public class ServiceCenter implements IComponentCenterService {

    private Map<String, IComponentHostService> moduleServiceMap = new HashMap<>();

    private ServiceCenter() {
    }

    private static volatile ServiceCenter instance;

    public static ServiceCenter getInstance() {
        if (instance == null) {
            synchronized (ServiceCenter.class) {
                if (instance == null) {
                    instance = new ServiceCenter();
                }
            }
        }
        return instance;
    }

    @Override
    public void register(@Nullable IComponentHostService service) {
        if (service == null) {
            return;
        }
        moduleServiceMap.put(service.getHost(), service);
        service.onCreate(Component.getApplication());
    }

    @Override
    public void register(@NonNull String host) {
        if (moduleServiceMap.containsKey(host)) {
            return;
        }
        IComponentHostService moduleService = findModuleService(host);
        register(moduleService);
    }

    @Override
    public void unregister(@Nullable IComponentHostService moduleService) {
        if (moduleService == null) {
            return;
        }
        moduleServiceMap.remove(moduleService.getHost());
        moduleService.onDestroy();
    }

    @Override
    public void unregister(@NonNull String host) {
        IComponentHostService moduleService = moduleServiceMap.get(host);
        unregister(moduleService);
    }

    @Nullable
    public IComponentHostService findModuleService(String host) {
        try {
            if (Component.getConfig().isOptimizeInit()) {
                return ASMUtil.findModuleServiceAsmImpl(host);
            } else {
                Class<? extends IComponentHostService> clazz = null;
                String className = ComponentUtil.genHostServiceClassName(host);
                clazz = (Class<? extends IComponentHostService>) Class.forName(className);
                return clazz.newInstance();
            }
        } catch (Exception ignore) {
            // ignore
        }
        return null;
    }

}