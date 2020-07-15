package com.cambodia.zhanbang.component.support;


import androidx.annotation.NonNull;

import com.cambodia.zhanbang.component.error.FragmentNotFoundException;
import com.cambodia.zhanbang.component.error.RxJavaException;
import com.cambodia.zhanbang.component.error.ServiceInvokeException;
import com.cambodia.zhanbang.component.error.ServiceNotFoundException;
import com.cambodia.zhanbang.component.error.UnknowException;
import com.cambodia.zhanbang.component.error.ignore.ActivityResultException;
import com.cambodia.zhanbang.component.error.ignore.InterceptorNotFoundException;
import com.cambodia.zhanbang.component.error.ignore.NavigationFailException;
import com.cambodia.zhanbang.component.error.ignore.TargetActivityNotFoundException;

import io.reactivex.plugins.RxJavaPlugins;

/**
 * 结合 RxJava 之后需要忽略的错误
 * time   : 2019/02/20
 */
public class RxErrorIgnoreUtil {

    private RxErrorIgnoreUtil() {
    }

    /**
     * 如果使用者不想处理错误的话,这些错误都可以被默认忽略
     */
    private static final Class[] DEFAULT_IGNORE_ERRORS = {
            // RxRouter 中需要忽略的错误
            NavigationFailException.class,
            ActivityResultException.class,
            TargetActivityNotFoundException.class,
            InterceptorNotFoundException.class,
            // RxServiceManager 中需要忽略的错误
            ServiceNotFoundException.class,
            // RxFragmentManager 中需要忽略的错误
            FragmentNotFoundException.class,
            ServiceInvokeException.class,
            RxJavaException.class,
            // 任何地方可以使用的一个未知的错误,也会被忽略
            UnknowException.class
    };

    private static final RxErrorConsumer errorConsumer = new RxErrorConsumer(RxJavaPlugins.getErrorHandler(), DEFAULT_IGNORE_ERRORS);


    /**
     * 忽略一些不想处理的错误
     */
    public static void ignoreError() {
        RxJavaPlugins.setErrorHandler(errorConsumer);
    }

    public static boolean isIgnore(@NonNull Throwable throwable) {
        return errorConsumer.isIgnore(throwable);
    }

}
