package com.moon.springsample.test;

import com.moon.springsample.BeanScopesApplication;
import com.moon.springsample.bean.SingletonScopeBean;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean 作用域失效问题解决方法测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-19 15:31
 * @description
 */
public class InvalidScopeTest {

    @Test
    public void testLazy() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanScopesApplication.class);
        SingletonScopeBean bean = context.getBean(SingletonScopeBean.class);
        // 多次获取，观察是否为同一个对象
        System.out.println(bean.getBean1());
        System.out.println(bean.getBean1());
        System.out.println(bean.getBean1());
    }

    @Test
    public void testProxyMode() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanScopesApplication.class);
        SingletonScopeBean bean = context.getBean(SingletonScopeBean.class);
        // 多次获取，观察是否为同一个对象
        System.out.println(bean.getBean2());
        System.out.println(bean.getBean2());
        System.out.println(bean.getBean2());
    }

    @Test
    public void testObjectFactory() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanScopesApplication.class);
        SingletonScopeBean bean = context.getBean(SingletonScopeBean.class);
        // 多次获取，观察是否为同一个对象
        System.out.println(bean.getBean3());
        System.out.println(bean.getBean3());
        System.out.println(bean.getBean3());
    }

    @Test
    public void testApplicationContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanScopesApplication.class);
        SingletonScopeBean bean = context.getBean(SingletonScopeBean.class);
        // 多次获取，观察是否为同一个对象
        System.out.println(bean.getBean4());
        System.out.println(bean.getBean4());
        System.out.println(bean.getBean4());
    }

}
