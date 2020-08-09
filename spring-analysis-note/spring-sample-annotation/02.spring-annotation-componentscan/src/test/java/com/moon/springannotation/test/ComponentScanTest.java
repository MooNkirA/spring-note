package com.moon.springannotation.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.service.AccountService;
import com.moon.springsample.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ComponentScan注解测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-2 12:17
 * @description
 */
public class ComponentScanTest {

    @Test
    public void componentScanBaseTest() {
        // 1. 获取基于注解的spinrg容器，使用传入字节码的构造函数创建容器。（这里故意不使用传入基础包的构造函数，如果这里配置了扫描包包含了测试层的位置，则看不出效果）
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2. 根据id或者类型去获取对应的bean实例
        UserService userService = context.getBean("userService", UserService.class);
        // 3. 调用对象方法
        userService.saveUser();
    }

    @Test
    public void componentScanBasePackagesTest() {
        // 1. 获取基于注解的spinrg容器，使用传入字节码的构造函数创建容器。（这里故意不使用传入基础包的构造函数，如果这里配置了扫描包包含了测试层的位置，则看不出效果）
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2. 根据id或者类型去获取对应的bean实例
        UserService userService = context.getBean("userService", UserService.class);
        // 3. 调用对象方法
        userService.saveUser();
    }

    @Test
    public void componentScanBasePackageClassesTest() {
        // 1. 获取基于注解的spinrg容器，使用基础包的构造函数，只扫描配置类所在的包。
        ApplicationContext context = new AnnotationConfigApplicationContext("com.moon.springsample.config");
        // 2. 根据id或者类型去获取对应的bean实例
        UserService userService = context.getBean("userService", UserService.class);
        // 3. 调用对象方法
        userService.saveUser();

        // 使用basePackageClasses方法扫描，测试指定字节码类所在的包及其子包所有的类是否被扫描到
        AccountService accoutService = context.getBean("accountService", AccountService.class);
        accoutService.deleteAccount();
    }

}
