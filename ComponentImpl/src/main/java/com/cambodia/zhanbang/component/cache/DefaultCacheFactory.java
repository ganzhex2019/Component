package com.cambodia.zhanbang.component.cache;


import androidx.annotation.NonNull;

import com.cambodia.zhanbang.component.Component;

public class DefaultCacheFactory implements Cache.Factory{

    private DefaultCacheFactory() {
    }

    public static final DefaultCacheFactory INSTANCE = new DefaultCacheFactory();

    @NonNull
    @Override
    public Cache build(CacheType type) {
        return new LruCache(type.calculateCacheSize(Component.getApplication()));
    }

}
