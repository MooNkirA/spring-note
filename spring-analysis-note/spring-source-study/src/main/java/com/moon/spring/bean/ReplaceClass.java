package com.moon.spring.bean;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * 使用replaced-method标签去替换指定类的某个方法，必须实现MethodReplacer接口
 * <p>重写的reimplement方法，会替换原来的方法</p>
 */
public class ReplaceClass implements MethodReplacer {

    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("I am replace method --> reimplement!");
        return null;
    }

}
