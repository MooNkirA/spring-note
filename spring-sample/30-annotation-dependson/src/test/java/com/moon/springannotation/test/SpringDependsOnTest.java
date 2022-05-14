package com.moon.springannotation.test;

import com.moon.springsample.config.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解 @DependsOn 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-30 17:55
 * @description
 */
public class SpringDependsOnTest {

    @Test
    public void dependsOnBasicTest() {
        // 1. 创建注解扫描的容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2. 启动容器，观察相关测试类的创建先后顺序
        context.start();
        /* 在没有添加任何关于创建顺序的注解的情况下，spring框架默认类的创建顺序是按扫描到当前包时，类的名称的字母排序决定的 */
        /* 通过 @DependsOn 注解，可以调整类与类之间依赖关系，来定制创建的顺序 */
    }

}
