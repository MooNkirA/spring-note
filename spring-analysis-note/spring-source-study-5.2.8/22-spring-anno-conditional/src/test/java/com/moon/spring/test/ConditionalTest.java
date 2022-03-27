package com.moon.spring.test;

import com.moon.spring.bean.ConditionalBean;
import com.moon.spring.config.AppConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * String @Conditional 注解测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-17 16:57
 * @description
 */
public class ConditionalTest {

    private final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    public void testConditionalOnBean() {
        ConditionalBean bean = context.getBean(ConditionalBean.class);
        System.out.println(bean);
    }

}
