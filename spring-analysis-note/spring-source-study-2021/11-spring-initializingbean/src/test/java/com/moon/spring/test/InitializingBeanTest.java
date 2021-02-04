package com.moon.spring.test;

import com.moon.spring.bean.CustomInitializingBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * InitializingBean 接口测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-3 11:12
 * @description
 */
public class InitializingBeanTest {

    private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    /**
     * InitializingBean接口基础使用测试
     */
    @Test
    public void testBaseInitializingBean() {
        CustomInitializingBean customInitializingBean = context.getBean("customInitializingBean", CustomInitializingBean.class);
        System.out.println(customInitializingBean);
        /*
         * 输出结果：
         * CustomInitializingBean无参构造方法执行了....
         * CustomInitializingBean类实现InitializingBeanr接口的afterPropertiesSet()方法执行了....
         * xml配置的CustomInitializingBean.initMethod()方法执行了....
         * com.moon.spring.bean.CustomInitializingBean@20d28811
         */
    }

}
