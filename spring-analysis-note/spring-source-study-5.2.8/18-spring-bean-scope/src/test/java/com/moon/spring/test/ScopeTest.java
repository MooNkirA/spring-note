package com.moon.spring.test;

import com.moon.spring.util.ContextUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * Spring Bean的实例多例与作用范围测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-13 11:50
 * @description
 */
public class ScopeTest {

    private final ApplicationContext context = ContextUtils.getAnnotationContext();

    /* 测试Spring Bean的实例作用范围 */
    @Test
    public void testPrototype() throws InterruptedException {
        /*
         * 测试单例与多例
         *      单例情况：每个线程获取的实例的hashCode都一样
         *      多例情况：每个线程获取的实例的hashCode都不一样
         *      多例情况，一个线程多次获取实例，其hashCode也是不一样
         */
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " --> " + context.getBean("prototypeBean"));
                System.out.println(Thread.currentThread().getName() + " --> " + context.getBean("prototypeBean"));
            }).start();
            Thread.sleep(1000);
        }
    }

    /* 测试Spring 自定义作用范围 */
    @Test
    public void testCustomScope() throws InterruptedException {
        /* 测试结果，与预期的效果一样，同一个线程时多次获取时均为同一个实例 */
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " --> " + context.getBean("customScopeBean"));
                System.out.println(Thread.currentThread().getName() + " --> " + context.getBean("customScopeBean"));
            }).start();
            Thread.sleep(1000);
        }
    }

    /* 测试request与Session作用域  --  待实现 */
    @Test
    public void requestSessoinScopeTest() {
        // context.getBean("requestScopeBean");
    }

}
