package com.cambodia.zhanbang.component.impl.fragment;

import android.os.Bundle;


import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cambodia.zhanbang.component.anno.support.CheckClassNameAnno;
import com.cambodia.zhanbang.component.support.CallNullable;
import com.cambodia.zhanbang.component.support.Function1;
import com.cambodia.zhanbang.component.support.Utils;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Fragment 的容器
 *
 */
@CheckClassNameAnno
public class FragmentManager {

    private FragmentManager() {
    }

    /**
     * Service 的集合
     */
    private static Map<String, Function1<Bundle, ? extends Fragment>> map =
            Collections.synchronizedMap(new HashMap<String, Function1<Bundle, ? extends Fragment>>());

    /**
     * 你可以注册一个服务,服务的初始化可以是 懒加载的
     *
     * @param flag 用 {@link com.cambodia.zhanbang.component.anno.FragmentAnno} 标记 {@link Fragment} 的字符串
     * @param function function
     */
    @AnyThread
    public static void register(@NonNull String flag,
                                @NonNull Function1<Bundle, ? extends Fragment> function) {
        Utils.checkNullPointer(flag, "flag");
        Utils.checkNullPointer(function, "function");
        map.put(flag, function);
    }

    @Nullable
    @AnyThread
    public static void unregister(@NonNull String flag) {
        map.remove(flag);
    }

    @Nullable
    @AnyThread
    public static Fragment get(@NonNull final String flag) {
        return get(flag, null);
    }

    @Nullable
    @AnyThread
    public static Fragment get(@NonNull final String flag, @Nullable final Bundle bundle) {
        Utils.checkNullPointer(flag, "fragment flag");
        return Utils.mainThreadCallNullable(new CallNullable<Fragment>() {
            @NonNull
            @Override
            public Fragment get() {
                Function1<Bundle, ? extends Fragment> function = map.get(flag);
                if (function == null) {
                    return null;
                } else {
                    return function.apply(bundle);
                }
            }
        });
    }

}
