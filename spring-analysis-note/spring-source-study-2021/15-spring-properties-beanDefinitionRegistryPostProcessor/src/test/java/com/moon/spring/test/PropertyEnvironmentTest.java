package com.moon.spring.test;

import com.moon.spring.bean.PropertyBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Environment 对象手动设置占位符
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-11 19:22
 * @description
 */
public class PropertyEnvironmentTest {

    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    /* 测试xml配置中的property占位符赋值 */
    @Test
    public void testPropertiesByXml() {
        PropertyBean bean = context.getBean("propertyBean", PropertyBean.class);
        System.out.println(bean.getUsername() + " :: " + bean.getPassword());
    }

}
