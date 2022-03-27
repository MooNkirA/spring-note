package com.moon.spring.test;

import com.moon.spring.component.RedisComponent;
import com.moon.spring.config.MySqlConfig;
import com.moon.spring.controller.UserController;
import com.moon.spring.dao.UserDao;
import com.moon.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * context:component-scan 自定义标签测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-10 18:04
 * @description
 */
public class ComponentScanTest {

    /* context:component-scan 标签流程debug测试 */
    @Test
    public void componentScanTest() {
        // 读取spring类路径下的配置文件
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        RedisComponent redisComponent = applicationContext.getBean("redisComponent", RedisComponent.class);
        System.out.println(redisComponent);
        MySqlConfig mySqlConfig = applicationContext.getBean("mySqlConfig", MySqlConfig.class);
        System.out.println(mySqlConfig);
        UserController userController = applicationContext.getBean("userController", UserController.class);
        System.out.println(userController);
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService);
        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
        System.out.println(userDao);
    }

}
