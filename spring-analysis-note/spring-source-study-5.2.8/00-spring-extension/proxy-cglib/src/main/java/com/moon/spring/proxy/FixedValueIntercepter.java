package com.moon.spring.proxy;

import net.sf.cglib.proxy.FixedValue;

/**
 * FixedValue 接口
 * 其 loadObject 方法，是用于完全替代被代理类调用的方法
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-19 22:31
 * @description
 */
public class FixedValueIntercepter implements FixedValue {

    @Override
    public Object loadObject() throws Exception {
        System.out.println("FixedValueIntercepter.loadObject()方法执行了....");
        return "loadObject value";
    }

}
