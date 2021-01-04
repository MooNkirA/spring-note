package com.moon.spring.bean;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * 用于测试 <bean> 子标签 <replaced-method>
 * 使用replaced-method标签去替换指定类的某个方法，必须实现MethodReplacer接口
 * <p>重写的reimplement方法，会替换原来的方法</p>
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-3 22:12
 * @description
 */
public class ReplaceBean implements MethodReplacer {

    /**
     * Reimplement the given method.
     *
     * @param obj    the instance we're reimplementing the method for
     * @param method the method to reimplement
     * @param args   arguments to the method
     * @return return value for the method
     */
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("I am replace method --> reimplement! params = " + args[0]);
        return null;
    }

}
