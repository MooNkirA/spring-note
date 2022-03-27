package com.moon.spring.proxy;

import com.moon.spring.service.GoodsServiceImpl;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Method;

/**
 * Cglib 代理工厂
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-19 22:02
 * @description
 */
public class CglibBeanFactory {

    public static Object getInstance() {
        // 创建增强器
        Enhancer enhancer = new Enhancer();
        // 设置被代理类（注意：是子类，非接口），会生成字节码文件并加载到jvm中
        enhancer.setSuperclass(GoodsServiceImpl.class);
        /*
         * 创建CallbackFilter对象，实现接口accept方法，在方法中指定调用相应的callback方法
         * 此方法的返回值是int整数，对于Callbacks数组中的下标
         */
        CallbackFilter callbackFilter = new CglibCallbackFilter();
        enhancer.setCallbackFilter(callbackFilter);

        // 创建几个Callback
        Callback callback1 = new GoodsServiceInterceptor1();
        Callback callback2 = new GoodsServiceInterceptor2();
        Callback callback3 = new GoodsServiceInterceptor3();
        // 这个NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
        Callback noop = NoOp.INSTANCE;
        // 此FixedValue接口实现，会直接替代被代理类调用的方法
        Callback fixdValueCallback = new FixedValueIntercepter();

        // 设置Callback回调数组（在CallbackFilter的accept方法返回值，相应此数组的下标，即会调用相应的Callback）
        Callback[] callbacks = {callback1, callback2, callback3, noop, fixdValueCallback};
        enhancer.setCallbacks(callbacks);
        // 创建代理
        return enhancer.create();
    }


    private static class GoodsServiceInterceptor1 implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            // 前置增强
            System.out.println(String.format("Interceptor1 执行 %s 前....", method.getName()));
            // 调用被代理的方法
            Object o = proxy.invokeSuper(obj, args);
            // 后置增强
            System.out.println(String.format("Interceptor1 执行 %s 后....", method.getName()));
            return o;
        }
    }

    private static class GoodsServiceInterceptor2 implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println(String.format("Interceptor2 执行 %s 前....", method.getName()));
            Object o = proxy.invokeSuper(obj, args);
            System.out.println(String.format("Interceptor2 执行 %s 后....", method.getName()));
            return o;
        }
    }

    private static class GoodsServiceInterceptor3 implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println(String.format("Interceptor3 执行 %s 前....", method.getName()));
            Object o = proxy.invokeSuper(obj, args);
            System.out.println(String.format("Interceptor3 执行 %s 后....", method.getName()));
            return o;
        }
    }

}
