package com.moon.springaop.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.domain.User;
import com.moon.springsample.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * 注解 @Around 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-9 15:39
 * @description
 */
public class SpringAroundTest {

    @Test
    public void aroundBasicTest() {
        // 1. 创建注解扫描的容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2.获取对象
        UserService userService = context.getBean("userService", UserService.class);
        // 3.执行方法
        User user = new User();
        user.setId("1");
        user.setUsername("石原里美");
        user.setNickname("十元");
        userService.saveUser(user);
    }

    /* @Around 注解案例测试 */
    @Test
    public void aroundDemoTest() {
        // 1. 创建注解扫描的容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2.获取对象
        UserService userService = context.getBean("userService", UserService.class);
        // 测试保存方法
        User user = new User();
        user.setId("1");
        user.setUsername("石原里美");
        user.setNickname("十元");
        userService.saveUser(user);
        System.out.println("-------------------------------------");

        // 测试根据id查询
        User user1 = userService.findById("1");
        System.out.println("执行了根据id查询: " + user1.toString());
        System.out.println("-------------------------------------");

        // 测试删除
        userService.delete("1");
        System.out.println("-------------------------------------");

        //测试更新
        userService.update(user);
        System.out.println("-------------------------------------");

        //测试查询所有
        List<User> users = userService.findAll();
        for (User u : users) {
            System.out.println("执行了查询所有: " + u.toString());
        }
    }

}
