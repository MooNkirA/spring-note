package com.moon.spring.proxy;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * CallbackFilter 接口实现类
 * 重写accept方法，方法返回值相应Callback数组的下标时，就会调用相应数组中相应下标的Callback
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-19 22:28
 * @description
 */
public class CglibCallbackFilter implements CallbackFilter {

    private final List<String> methodList = Arrays.asList("addGoods", "editGoods", "queryGoods", "deleteGoods");

    @Override
    public int accept(Method method) {
        String methodName = method.getName();
        int i = methodList.indexOf(methodName);
        return i < 0 ? 4 : i;
    }
}
