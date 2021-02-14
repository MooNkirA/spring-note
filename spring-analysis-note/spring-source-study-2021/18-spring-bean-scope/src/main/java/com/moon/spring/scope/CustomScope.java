package com.moon.spring.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * 自定义bean作用域，需要实现Scope接口
 * <p>此自定义作用域的需求是，在同一个线程中获取都是同一个实例，不同线程获取不同实例</p>
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-13 14:36
 * @description
 */
public class CustomScope implements Scope {

    private final ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    /**
     * 获取bean实例的方法，此方法可以实现管理生成bean实例作用域逻辑
     *
     * @param name
     * @param objectFactory
     * @return
     */
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        System.out.println("=====自定义作用域CustomScope.get()执行=====");

        // 如果当前线程存在创建完成的实例，直接返回
        if (threadLocal.get() != null) {
            return threadLocal.get();
        }

        // 此处调用ObjectFactory接口的getObject方法，相应源码就是调用spring框架的createbean方法获得一个实例
        Object object = objectFactory.getObject();
        // 设置到ThreadLocal容器并返回
        threadLocal.set(object);
        return object;
    }

    @Override
    public Object remove(String name) {
        Object o = threadLocal.get();
        threadLocal.remove();
        return o;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
