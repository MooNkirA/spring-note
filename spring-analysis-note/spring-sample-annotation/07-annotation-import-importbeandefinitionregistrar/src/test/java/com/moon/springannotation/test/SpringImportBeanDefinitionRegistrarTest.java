package com.moon.springannotation.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.service.UserService;
import com.moon.springsample.service.utils.LogUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 自定义 ImportBeanDefinitionRegistrar 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-29 15:49
 * @description
 */
public class SpringImportBeanDefinitionRegistrarTest {

    /* 自定义ImportBeanDefinitionRegistrar测试 */
    @Test
    public void customImportBeanDefinitionRegistrarTest() {
        // 1. 创建注解扫描容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2. 获取相关对象，注意：使用ImportBeanDefinitionRegistrar注册器，bean对象默认在ioc容器的唯一标识是类的名称，首字母小写
        UserService userService = context.getBean("userServiceImpl", UserService.class);
        userService.saveUser();

        LogUtil logUtil = context.getBean("logUtil", LogUtil.class);
        logUtil.saveLog();
    }

}
