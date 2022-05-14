package com.moon.springannotation.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.service.UserService;
import com.moon.springsample.service.utils.LogUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 自定义 ImportSelector 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-29 15:49
 * @description
 */
public class SpringImportSelectorTest {

    /* 自定义ImportSelector测试 */
    @Test
    public void customImportSelectorTest() {
        // 1. 创建注解扫描容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2. 获取相关对象，注意：使用ImportSelector导入器，bean对象在ioc容器的唯一标识是类的全限定名称
        UserService userService = context.getBean("com.moon.springsample.service.impl.UserServiceImpl", UserService.class);
        userService.saveUser();

        LogUtil logUtil = context.getBean("com.moon.springsample.service.utils.LogUtil", LogUtil.class);
        logUtil.saveLog();
    }

}
