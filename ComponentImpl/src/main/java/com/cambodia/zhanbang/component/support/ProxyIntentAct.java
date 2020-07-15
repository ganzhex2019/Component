package com.cambodia.zhanbang.component.support;

import android.app.Activity;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.cambodia.zhanbang.component.ComponentActivityStack;
import com.cambodia.zhanbang.component.impl.ProxyIntentBuilder;
import com.cambodia.zhanbang.component.impl.Router;

/**
 * 此界面是一个无界面的, 当使用者通过 {@link ProxyIntentBuilder}
 * 构建一个代理 {@link android.content.Intent} 之后. 此 `Intent` 可以交给
 * 任何一个可以发起此 `Intent` 的地方, 比如：
 * 1. 系统小部件
 * 2. 消息栏
 * 3. 任何 PendingIntent 使用到的地方
 */
public class ProxyIntentAct extends FragmentActivity {

    public static final String EXTRA_ROUTER_PROXY_INTENT = "router_proxy_intent";
    public static final String EXTRA_ROUTER_PROXY_INTENT_URL = "router_proxy_intent_url";
    public static final String EXTRA_ROUTER_PROXY_INTENT_BUNDLE = "router_proxy_intent_bundle";
    public static final String EXTRA_ROUTER_PROXY_INTENT_OPTIONS = "router_proxy_intent_options";
    public static final String EXTRA_ROUTER_PROXY_INTENT_FLAGS = "router_proxy_intent_flags";
    public static final String EXTRA_ROUTER_PROXY_INTENT_CATEGORIES = "router_proxy_intent_categories";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取数据
        Bundle bundle = getIntent().getExtras();
        Activity launchActivity = ComponentActivityStack
                .getInstance()
                .getTopActivityExcept(getClass());
        final boolean isUseSelfActivity = launchActivity == null;
        if (launchActivity == null) {
            launchActivity = this;
        }
        // 如果不是使用此 Activity 跳转, 那么立即销毁自己
        if (!isUseSelfActivity) {
            finish();
        }
        // 发起跳转
        Router.with(launchActivity)
                .proxyBundle(bundle)
                .afterEventAction(new Action() {
                    @Override
                    public void run() {
                        if (isUseSelfActivity) {
                            finish();
                        }
                    }
                })
                .forward();
    }

}
