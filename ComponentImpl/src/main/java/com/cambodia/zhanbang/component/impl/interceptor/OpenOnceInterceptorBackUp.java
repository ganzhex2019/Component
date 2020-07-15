package com.cambodia.zhanbang.component.impl.interceptor;

import android.net.Uri;

import com.cambodia.zhanbang.component.Component;
import com.cambodia.zhanbang.component.error.ignore.NavigationFailException;
import com.cambodia.zhanbang.component.impl.RouterInterceptor;

/**
 * 这个拦截器必须在其他任何一个拦截器之前执行
 * 从根本上限制同一个界面在一秒钟内只能打开一次,这个拦截器会被框架最先执行
 * note: 这个拦截器没有连同 {@link Uri#getScheme()} 一起判断,其实应该一起的,
 * 但是现实中应该也不会出现一秒钟 host 和 path 都相同的两次路由了
 *
 * time   : 2019/01/23
 *
 */
public class OpenOnceInterceptorBackUp implements RouterInterceptor {

    private OpenOnceInterceptorBackUp() {
    }

    private static class SingletonInstance {
        private static final OpenOnceInterceptorBackUp INSTANCE = new OpenOnceInterceptorBackUp();
    }

    public static OpenOnceInterceptorBackUp getInstance() {
        return OpenOnceInterceptorBackUp.SingletonInstance.INSTANCE;
    }

    private String preHost;
    private String prePath;
    /**
     * 记录上一个界面跳转的时间
     */
    private long preTargetTime;

    @Override
    public void intercept(Chain chain) throws Exception {
        Uri uri = chain.request().uri;
        String currentHost = uri.getHost();
        String currentPath = uri.getPath();
        // 调试的情况下可能会失效,因为你断点打到这里慢慢的往下走那么可能时间已经过了一秒,就失去了限制的作用
        long currentTime = System.currentTimeMillis();
        // 如果匹配了
        if (currentHost.equals(preHost) &&
                currentPath.equals(prePath) &&
                (currentTime - preTargetTime) < Component.getConfig().getRouteRepeatCheckDuration()) {
            chain.callback().onError(new NavigationFailException("same request can't launch twice in a second, target uri is：" + uri.toString()));
        } else {
            preHost = currentHost;
            prePath = currentPath;
            preTargetTime = currentTime;
            // 放过执行
            chain.proceed(chain.request());
        }
    }

}
