package com.cambodia.zhanbang.component.impl.fragment;

import android.app.Application;


import androidx.annotation.NonNull;

import com.cambodia.zhanbang.component.fragment.IComponentHostFragment;

import java.util.Collections;
import java.util.Set;

/**
 * 空实现,每一个模块的 Fragment 生成类需要继承的
 *
 */
abstract class MuduleFragmentImpl implements IComponentHostFragment {

    @Override
    public void onCreate(Application app) {
    }

    @Override
    public void onDestroy() {
    }

    @NonNull
    @Override
    public Set<String> getFragmentNameSet() {
        return Collections.EMPTY_SET;
    }

}
