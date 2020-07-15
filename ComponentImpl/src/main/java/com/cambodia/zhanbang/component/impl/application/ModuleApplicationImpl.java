package com.cambodia.zhanbang.component.impl.application;

import android.app.Application;


import androidx.annotation.NonNull;

import com.cambodia.zhanbang.component.ComponentUtil;
import com.cambodia.zhanbang.component.application.IComponentApplication;
import com.cambodia.zhanbang.component.application.IComponentHostApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个类是生成的 Module Application 类的父类
 * 如果名称更改了,请配置到
 * {@link ComponentUtil#IMPL_OUTPUT_PKG}
 * 和 {@link ComponentUtil#MODULE_APPLICATION_IMPL_CLASS_NAME} 上
 *
 * @author xiaojinzi
 */
abstract class ModuleApplicationImpl implements IComponentHostApplication {

    /**
     * Application的集合
     */
    protected List<IComponentApplication> moduleAppList = new ArrayList<>();

    /**
     * 是否初始化了list,懒加载
     */
    protected boolean hasInitList = false;

    protected void initList() {
        hasInitList = true;
    }

    @Override
    public void onCreate(@NonNull Application app) {
        if (!hasInitList) {
            initList();
        }
        if (moduleAppList == null) {
            return;
        }
        for (IComponentApplication application : moduleAppList) {
            application.onCreate(app);
        }
    }

    @Override
    public void onDestroy() {
        if (!hasInitList) {
            initList();
        }
        if (moduleAppList == null) {
            return;
        }
        for (IComponentApplication application : moduleAppList) {
            application.onDestroy();
        }
    }

}
