package com.cambodia.zhanbang.component.impl.service;

import android.app.Application;

import com.cambodia.zhanbang.component.service.IComponentHostService;

/**
 * 空实现,每一个模块的 service 生成类需要继承的
 *
 */
abstract class MuduleServiceImpl implements IComponentHostService {
    @Override
    public void onCreate(Application app) {
    }

    @Override
    public void onDestroy() {
    }
}
