package com.cambodia.zhanbang.component.bean;

import com.cambodia.zhanbang.component.anno.ImplicitUseAnno;

public class ActivityAttrDocBean {

    @ImplicitUseAnno
    private String[] attrKey;
    @ImplicitUseAnno
    private String attrType;

    @ImplicitUseAnno
    public String[] getAttrKey() {
        return attrKey;
    }

    public void setAttrKey(String[] attrKey) {
        this.attrKey = attrKey;
    }

    @ImplicitUseAnno
    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

}
