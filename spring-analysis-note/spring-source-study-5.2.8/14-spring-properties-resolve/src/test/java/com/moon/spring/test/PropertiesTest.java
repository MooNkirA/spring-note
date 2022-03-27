package com.moon.spring.test;

import com.moon.spring.bean.PlaceholderValueBean;
import com.moon.spring.bean.PropertyBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 属性注入配置文件值测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-9 14:15
 * @description
 */
public class PropertiesTest {

    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    /* 测试xml配置中的property占位符赋值 */
    @Test
    public void testPropertiesByXml() {
        PropertyBean bean = context.getBean("propertyBean", PropertyBean.class);
        System.out.println(bean.getUsername() + " :: " + bean.getPassword());
    }

    /* 测试xml配置中的property占位符赋值 */
    @Test
    public void testPlaceholderByValueAnnotation() {
        PlaceholderValueBean bean = context.getBean("placeholderValueBean", PlaceholderValueBean.class);
        System.out.println(bean.getName() + " :: " + bean.getPassword());
    }

}
