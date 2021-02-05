package com.moon.spring.test;

import com.moon.spring.component.CircularRefA;
import com.moon.spring.component.CircularRefB;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 循环依赖测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-5 16:52
 * @description
 */
public class CircularReferenceTest {

    private ApplicationContext context = new AnnotationConfigApplicationContext("com.moon.spring");

    /* 测试两个单例对象的循环依赖 */
    @Test
    public void testTwoSingleton() {
        CircularRefA beanA = context.getBean("circularRefA", CircularRefA.class);
        CircularRefB beanB = context.getBean("circularRefB", CircularRefB.class);
        System.out.println(beanA.getCircularRefB());
        System.out.println(beanB.getCircularRefA());
    }

}
