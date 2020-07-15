package com.cambodia.zhanbang.component;

/**
 * time   : 2018/07/26

 */
public class ComponentUtil {

    private ComponentUtil() {
    }

    /**
     * 1.这是注解驱动器生成类的时候的目录
     * 2.这也是一些写好的实现类的包名字,不要轻易更改,如果要更改,请仔细比对
     * ComponentApiImpl 模块的实现类的位置
     */
    public static final String IMPL_OUTPUT_PKG = "com.cambodia.zhanbang.component.impl";

    public static final String IMPL_OUTPUT_PKG_PATH = "com/cambodia/zhanbang/component/impl";

    /**
     * 点
     */
    public static final String DOT = ".";

    /**
     * 生成的文件名称的后缀
     */
    public static final String UIROUTER = "RouterGenerated";

    /**
     * 生成的降级文件名称的后缀
     */
    public static final String UIROUTER_DEGRADE = "RouterDegradeGenerated";

    /**
     * 生成的路由接口的实现类的后缀
     */
    public static final String UIROUTERAPI = "RouterApiGenerated";

    /**
     * 生成的文件名称的后缀
     */
    public static final String MODULE_APPLCATION = "ModuleApplicationGenerated";

    /**
     * 生成的文件名称的后缀
     */
    public static final String MODULE_APPLCATION_DEFAULT = "ModuleApplicationGeneratedDefault";

    /**
     * 生成的文件名称的后缀
     */
    public static final String SERVICE = "ServiceGenerated";

    /**
     * 生成的文件名称的后缀
     */
    public static final String INTERCEPTOR = "InterceptorGenerated";

    /**
     * 生成的文件名称的后缀
     */
    public static final String Fragment = "FragmentGenerated";

    public static final String GLOBAL_INTERCEPTOR_ANNO_CLASS_NAME = "com.cambodia.zhanbang.component.anno.GlobalInterceptorAnno";
    public static final String INTERCEPTOR_ANNO_CLASS_NAME = "com.cambodia.zhanbang.component.anno.InterceptorAnno";
    public static final String ROUTER_ANNO_CLASS_NAME = "com.cambodia.zhanbang.component.anno.RouterAnno";
    public static final String FRAGMENTANNO_CLASS_NAME = "com.cambodia.zhanbang.component.anno.FragmentAnno";
    public static final String ROUTER_DEGRADE_ANNO_CLASS_NAME = "com.cambodia.zhanbang.component.anno.RouterDegradeAnno";
    public static final String MODULE_APP_ANNO_CLASS_NAME = "com.cambodia.zhanbang.component.anno.ModuleAppAnno";
    public static final String SERVICE_ANNO_CLASS_NAME = "com.cambodia.zhanbang.component.anno.ServiceAnno";
    public static final String PARAMETERANNO_CLASS_NAME = "com.cambodia.zhanbang.component.anno.ParameterAnno";
    public static final String ATTR_VALUE_AUTOWIREDANNO_CLASS_NAME = "com.cambodia.zhanbang.component.anno.AttrValueAutowiredAnno";
    public static final String SERVICEAUTOWIREDANNO_CLASS_NAME = "com.cambodia.zhanbang.component.anno.ServiceAutowiredAnno";
    public static final String URIAUTOWIREDANNO_CLASS_NAME = "com.cambodia.zhanbang.component.anno.UriAutowiredAnno";
    public static final String ROUTERAPIANNO_CLASS_NAME = "com.cambodia.zhanbang.component.anno.router.RouterApiAnno";


    // 路由框架基础实现类的全类名

    public static final String UIROUTER_IMPL_CLASS_NAME = IMPL_OUTPUT_PKG + DOT + "ModuleRouterImpl";
    public static final String UIROUTER_DEGRADE_IMPL_CLASS_NAME = IMPL_OUTPUT_PKG + DOT + "ModuleRouterDegradeImpl";
    public static final String MODULE_APPLICATION_IMPL_CLASS_NAME = IMPL_OUTPUT_PKG + DOT + "application" + DOT + "ModuleApplicationImpl";
    public static final String SERVICE_IMPL_CLASS_NAME = IMPL_OUTPUT_PKG + DOT + "service" + DOT + "MuduleServiceImpl";
    public static final String INTERCEPTOR_IMPL_CLASS_NAME = IMPL_OUTPUT_PKG + DOT + "interceptor" + DOT + "MuduleInterceptorImpl";
    public static final String FRAGMENT_IMPL_CLASS_NAME = IMPL_OUTPUT_PKG + DOT + "fragment" + DOT + "MuduleFragmentImpl";

    /**
     * 首字母小写
     */
    public static String firstCharUpperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    public static String genRouterApiImplClassName(Class apiClass) {
        return apiClass.getName() + UIROUTERAPI;
    }

    public static String genHostModuleApplicationClassName(String host) {
        return IMPL_OUTPUT_PKG + DOT + "application" + DOT + firstCharUpperCase(host) + MODULE_APPLCATION;
    }

    public static String genDefaultHostModuleApplicationClassName(String host) {
        return IMPL_OUTPUT_PKG + DOT + "application" + DOT + firstCharUpperCase(host) + MODULE_APPLCATION_DEFAULT;
    }

    public static String genHostRouterClassName(String host) {
        return IMPL_OUTPUT_PKG + DOT + firstCharUpperCase(host) + UIROUTER;
    }

    public static String genHostRouterDegradeClassName(String host) {
        return IMPL_OUTPUT_PKG + DOT + firstCharUpperCase(host) + UIROUTER_DEGRADE;
    }

    public static String genHostServiceClassName(String host) {
        return IMPL_OUTPUT_PKG + DOT + "service" + DOT + firstCharUpperCase(host) + SERVICE;
    }

    public static String genHostInterceptorClassName(String host) {
        return IMPL_OUTPUT_PKG + DOT + "interceptor" + DOT + firstCharUpperCase(host) + INTERCEPTOR;
    }

    public static String genHostFragmentClassName(String host) {
        return IMPL_OUTPUT_PKG + DOT + "fragment" + DOT + firstCharUpperCase(host) + Fragment;
    }

}
