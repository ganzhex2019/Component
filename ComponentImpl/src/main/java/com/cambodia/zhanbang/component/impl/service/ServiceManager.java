package com.cambodia.zhanbang.component.impl.service;



import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cambodia.zhanbang.component.anno.support.CheckClassNameAnno;
import com.cambodia.zhanbang.component.support.CallNullable;
import com.cambodia.zhanbang.component.support.Callable;
import com.cambodia.zhanbang.component.support.Utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务的容器,使用这个服务容器你需要判断获取到的服务是否为空,对于使用者来说还是比较不方便的
 * 建议使用 Service 扩展的版本 RxService
 *
 */
@CheckClassNameAnno
public class ServiceManager {

    private ServiceManager() {
    }

    /**
     * Service 的集合. 线程安全的
     */
    private static Map<Class, Callable<?>> serviceMap =
            Collections.synchronizedMap(new HashMap<Class, Callable<?>>());

    /**
     * 你可以注册一个服务,服务的初始化可以是 懒加载的
     */
    @AnyThread
    public static <T> void register(@NonNull Class<T> tClass, @NonNull Callable<? extends T> callable) {
        Utils.checkNullPointer(tClass, "tClass");
        Utils.checkNullPointer(callable, "callable");
        serviceMap.put(tClass, callable);
    }

    @Nullable
    @AnyThread
    public static <T> void unregister(@NonNull Class<T> tClass) {
        serviceMap.remove(tClass);
    }

    /**
     * 如果不是主线程会卡住线程, 让最终的用户自定义的对象在主线程中被创建
     *
     * @param tClass 目标对象的 Class 对象
     * @param <T>    目标对象的实例对象
     * @return 目标对象的实例对象
     */
    @Nullable
    @AnyThread
    public static <T> T get(@NonNull final Class<T> tClass) {
        return Utils.mainThreadCallNullable(new CallNullable<T>() {
            @NonNull
            @Override
            public T get() {
                Callable<?> callable = serviceMap.get(tClass);
                if (callable == null) {
                    return null;
                } else {
                    return (T) Utils.checkNullPointer(callable.get());
                }
            }
        });
    }

}
