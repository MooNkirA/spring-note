package com.moon.springannotation.test;

import com.moon.springsample.bean.CustomBean;
import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.processor.MyBeanPostProcessor;
import com.moon.springsample.service.UserService;
import com.moon.springsample.utils.LogUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring 容器对象的生命周期测试
 * 涉及 @PostConstruct 与 @PreDestroy 、 @Scope
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 22:01
 * @description
 */
public class SpringLifecycleTest {

    @Test
    public void lifecycleBasicTest() {
        // 1. 创建注解扫描的容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        System.out.println("************* 容器创建完毕 *************");

        // 2. 获取容器中的bean对象，并输出
        // CustomBean customBean = context.getBean("customBean", CustomBean.class);
        // System.out.println(customBean);
        // UserService userService = context.getBean("userService", UserService.class);
        // System.out.println(userService);
        // LogUtil logUtil = context.getBean("logUtil", LogUtil.class);
        // System.out.println(logUtil);
        MyBeanPostProcessor myBeanPostProcessor = context.getBean("myBeanPostProcessor", MyBeanPostProcessor.class);
        System.out.println(myBeanPostProcessor);

        // 3. 关闭容器，观察单例对象的销毁前的方法
        context.close();
    }

}
