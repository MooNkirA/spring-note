package com.moon.springsample;

import com.moon.springsample.bean.Cat;
import com.moon.springsample.config.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * BeanPostProcessor 实现测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-16 15:40
 * @description
 */
public class BeanPostProcessorTest {

    /* 基础 BeanPostProcessor 接口实现测试 */
    @Test
    public void TestBasicBeanPostProcessor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        System.out.println(context.getBean(Cat.class));
    }

}
