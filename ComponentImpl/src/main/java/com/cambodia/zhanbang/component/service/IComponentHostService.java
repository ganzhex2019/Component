package com.cambodia.zhanbang.component.service;


import com.cambodia.zhanbang.component.application.IComponentApplication;
import com.cambodia.zhanbang.component.support.IHost;

/**
 * 每个模块的接口,需要有生命周期的通知
 */
public interface IComponentHostService extends IComponentApplication, IHost {
}
