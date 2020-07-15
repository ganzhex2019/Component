package com.cambodia.zhanbang.component.bean;


import androidx.annotation.NonNull;

import com.cambodia.zhanbang.component.anno.support.CheckClassNameAnno;
import com.cambodia.zhanbang.component.impl.RouterDegrade;

@CheckClassNameAnno
public class RouterDegradeBean {

    /**
     * 优先级
     */
    private int priority;

    /**
     * 这个目标 Activity Class,可能为空,因为可能标记在静态方法上
     */
    @NonNull
    private Class<? extends RouterDegrade> targetClass;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @NonNull
    public Class<? extends RouterDegrade> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(@NonNull Class<? extends RouterDegrade> targetClass) {
        this.targetClass = targetClass;
    }

}
