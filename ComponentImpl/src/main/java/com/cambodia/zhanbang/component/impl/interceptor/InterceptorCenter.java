package com.cambodia.zhanbang.component.impl.interceptor;



import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cambodia.zhanbang.component.Component;
import com.cambodia.zhanbang.component.ComponentUtil;
import com.cambodia.zhanbang.component.anno.support.CheckClassNameAnno;
import com.cambodia.zhanbang.component.error.InterceptorNameExistException;
import com.cambodia.zhanbang.component.impl.RouterInterceptor;
import com.cambodia.zhanbang.component.interceptor.IComponentCenterInterceptor;
import com.cambodia.zhanbang.component.interceptor.IComponentHostInterceptor;
import com.cambodia.zhanbang.component.support.ASMUtil;
import com.cambodia.zhanbang.component.support.RouterInterceptorCache;
import com.cambodia.zhanbang.component.support.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 中央拦截器
 * time   : 2018/12/26
 */
@CheckClassNameAnno
public class InterceptorCenter implements IComponentCenterInterceptor {

    private InterceptorCenter() {
    }

    /**
     * 单例对象
     */
    private static volatile InterceptorCenter instance;

    /**
     * 获取单例对象
     *
     * @return
     */
    public static InterceptorCenter getInstance() {
        if (instance == null) {
            synchronized (InterceptorCenter.class) {
                if (instance == null) {
                    instance = new InterceptorCenter();
                }
            }
        }
        return instance;
    }

    /**
     * 子拦截器对象管理 map
     */
    private Map<String, IComponentHostInterceptor> moduleInterceptorMap = new HashMap<>();

    /**
     * 公共的拦截器列表
     */
    private List<RouterInterceptor> mGlobalInterceptorList = new ArrayList<>();

    /**
     * 每个业务组件的拦截器 name --> Class 映射关系的总的集合
     * 这种拦截器不是全局拦截器,是随时随地使用的拦截器,见 {@link com.cambodia.zhanbang.component.impl.Navigator#interceptorNames(String...)}
     */
    private Map<String, Class<? extends RouterInterceptor>> mInterceptorMap = new HashMap<>();

    /**
     * 是否公共的拦截器列表发生变化
     */
    private boolean isInterceptorListHaveChange = false;

    /**
     * 获取全局拦截器
     */
    @MainThread
    public List<RouterInterceptor> getGlobalInterceptorList() {
        if (isInterceptorListHaveChange) {
            loadAllGlobalInterceptor();
            isInterceptorListHaveChange = false;
        }
        return mGlobalInterceptorList;
    }

    @Override
    public void register(@Nullable IComponentHostInterceptor interceptor) {
        if (interceptor == null) {
            return;
        }
        isInterceptorListHaveChange = true;
        moduleInterceptorMap.put(interceptor.getHost(), interceptor);
        // 子拦截器列表
        Map<String, Class<? extends RouterInterceptor>> childInterceptorMap = interceptor.getInterceptorMap();
        mInterceptorMap.putAll(childInterceptorMap);
    }

    @Override
    public void register(@NonNull String host) {
        if (moduleInterceptorMap.containsKey(host)) {
            return;
        }
        IComponentHostInterceptor moduleInterceptor = findModuleInterceptor(host);
        register(moduleInterceptor);
    }

    @Override
    public void unregister(@Nullable IComponentHostInterceptor interceptor) {
        if (interceptor == null) {
            return;
        }
        moduleInterceptorMap.remove(interceptor.getHost());
        isInterceptorListHaveChange = true;
        // 子拦截器列表
        Map<String, Class<? extends RouterInterceptor>> childInterceptorMap = interceptor.getInterceptorMap();
        for (Map.Entry<String, Class<? extends RouterInterceptor>> entry : childInterceptorMap.entrySet()) {
            mInterceptorMap.remove(entry.getKey());
            RouterInterceptorCache.removeCache(entry.getValue());
        }
    }

    @Override
    public void unregister(@NonNull String host) {
        IComponentHostInterceptor moduleInterceptor = moduleInterceptorMap.get(host);
        unregister(moduleInterceptor);
    }

    /**
     * 按顺序弄好所有全局拦截器
     */
    @MainThread
    private void loadAllGlobalInterceptor() {
        mGlobalInterceptorList.clear();
        List<InterceptorBean> totalList = new ArrayList<>();
        // 加载各个子拦截器对象中的拦截器列表
        for (Map.Entry<String, IComponentHostInterceptor> entry : moduleInterceptorMap.entrySet()) {
            List<InterceptorBean> list = entry.getValue().globalInterceptorList();
            totalList.addAll(list);
        }
        // 排序所有的拦截器对象,按照优先级排序
        Collections.sort(totalList, new Comparator<InterceptorBean>() {
            @Override
            public int compare(InterceptorBean o1, InterceptorBean o2) {
                if (o1.priority == o2.priority) {
                    return 0;
                } else if (o1.priority > o2.priority) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        for (InterceptorBean interceptorBean : totalList) {
            mGlobalInterceptorList.add(interceptorBean.interceptor);
        }
    }

    @Nullable
    @MainThread
    public IComponentHostInterceptor findModuleInterceptor(String host) {
        Utils.checkMainThread();
        try {
            if (Component.getConfig().isOptimizeInit()) {
                return ASMUtil.findModuleInterceptorAsmImpl(host);
            } else {
                Class<? extends IComponentHostInterceptor> clazz = null;
                String className = ComponentUtil.genHostInterceptorClassName(host);
                clazz = (Class<? extends IComponentHostInterceptor>) Class.forName(className);
                return clazz.newInstance();
            }
        } catch (Exception ignore) {
            // ignore
        }
        return null;
    }

    @Nullable
    @Override
    @MainThread
    public RouterInterceptor getByName(@Nullable String interceptorName) {
        if (interceptorName == null) {
            return null;
        }
        // 先到缓存中找
        RouterInterceptor result = null;
        // 拿到拦截器的 Class 对象
        Class<? extends RouterInterceptor> interceptorClass = mInterceptorMap.get(interceptorName);
        if (interceptorClass == null) {
            result = null;
        } else {
            result = RouterInterceptorCache.getInterceptorByClass(interceptorClass);
        }
        return result;
    }

    /**
     * 做拦截器的名称是否重复的工作
     */
    @MainThread
    public void check() {
        Utils.checkMainThread();
        Set<String> set = new HashSet<>();
        for (Map.Entry<String, IComponentHostInterceptor> entry : moduleInterceptorMap.entrySet()) {
            IComponentHostInterceptor childInterceptor = entry.getValue();
            if (childInterceptor == null) {
                continue;
            }
            Set<String> childInterceptorNames = childInterceptor.getInterceptorNames();
            if (childInterceptorNames.isEmpty()) {
                continue;
            }
            for (String interceptorName : childInterceptorNames) {
                if (set.contains(interceptorName)) {
                    throw new InterceptorNameExistException("the interceptor's name is exist：" + interceptorName);
                }
                set.add(interceptorName);
            }
        }
    }

}
