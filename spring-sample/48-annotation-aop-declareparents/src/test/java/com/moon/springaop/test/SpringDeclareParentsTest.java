package com.moon.springaop.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.domain.User;
import com.moon.springsample.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解 @DeclareParents 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-10 14:17
 * @description
 */
public class SpringDeclareParentsTest {

    @Test
    public void declareParentsBasicTest() {
        // 1. 创建注解扫描的容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2.获取对象
        UserService userService = context.getBean("userService", UserService.class);
        // 3.执行方法
        User user = new User();
        user.setId("1");
        user.setUsername("石原里美");
        user.setNickname("十元的孙子");

        // 第一种触发@DeclareParents扩展接口的方式：在使用时自行强转新引入接口类型，然后调用方法。
        // ValidateExtensionService validate = (ValidateExtensionService) userService;
        // 调用扩展接口的方法
        /*if (validate.checkUser(user)) {
            userService.saveUser(user);
        } else {
            System.out.println("用户信息校验不通过");
        }*/

        /*
         * 第二种触发@DeclareParents扩展接口的方式：在通知类中，使用this关键字，引入新目标类对象，调用方法触发。
         *   所以这里按原来逻辑直接调用方法即可
         */
        userService.saveUser(user);
    }

}
