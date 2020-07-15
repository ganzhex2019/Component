package com.cambodia.zhanbang.component.cache;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cambodia.zhanbang.component.anno.support.CheckClassNameAnno;

/**
 * Class 的缓存的工具类
 */
@CheckClassNameAnno
public class ClassCache {

    private static final Cache<Class, Object> classCache =
            DefaultCacheFactory.INSTANCE.build(CacheType.CLASS_CACHE);

    public static synchronized final void put(@NonNull Class clazz, @Nullable Object o) {
        classCache.put(clazz, o);
    }

    @Nullable
    public static synchronized final <T> T get(@NonNull Class clazz) {
        return (T) classCache.get(clazz);
    }

    @Nullable
    public static synchronized final <T> T remove(@NonNull Class clazz) {
        return (T) classCache.remove(clazz);
    }

    public static synchronized final void clear() {
        classCache.clear();
    }

}
