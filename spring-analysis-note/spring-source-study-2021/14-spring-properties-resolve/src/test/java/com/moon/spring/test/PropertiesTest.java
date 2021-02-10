package com.moon.spring.test;

import com.moon.spring.bean.PropertiesXmlBean;
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

    private ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    @Test
    public void testPropertiesByXml() {
        PropertiesXmlBean bean = context.getBean("propertiesXmlBean", PropertiesXmlBean.class);
        System.out.println(bean.getName() + " :: " + bean.getPassword());
    }

}
