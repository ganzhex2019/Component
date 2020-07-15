package com.cambodia.zhanbang.component.cache;

import android.app.ActivityManager;
import android.content.Context;

import androidx.annotation.NonNull;

import com.cambodia.zhanbang.component.support.Utils;


/**
 * 构建 {@link Cache} 时,使用 {@link CacheType} 中声明的类型,来区分不同的模块
 * 从而为不同的模块构建不同的缓存策略
 */
public interface CacheType {

    int CLASS_CACHE_TYPE_ID = 0;

    /**
     * 缓存 {@link Class} 的容器
     */
    CacheType CLASS_CACHE = new CacheType() {

        private static final int MAX_SIZE = 25;

        @Override
        public int getCacheTypeId() {
            return CLASS_CACHE_TYPE_ID;
        }

        @Override
        public int calculateCacheSize(Context context) {
            Utils.checkNullPointer(context, "context");
            ActivityManager activityManager =
                    (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            int targetMemoryCacheSize;
            if (Utils.isLowMemoryDevice(activityManager)) {
                targetMemoryCacheSize = activityManager.getMemoryClass() / 6;
            } else {
                targetMemoryCacheSize = activityManager.getMemoryClass() / 4;
            }
            if (targetMemoryCacheSize > MAX_SIZE) {
                return MAX_SIZE;
            }
            return targetMemoryCacheSize;
        }
    };


    /**
     * 返回框架内需要缓存的模块对应的 {@code id}
     */
    int getCacheTypeId();

    /**
     * 计算对应模块需要的缓存大小
     */
    int calculateCacheSize(@NonNull Context context);

}
