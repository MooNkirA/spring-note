package com.moon.spring.test;

import com.moon.spring.bean.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring 框架基础使用
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-12-13 23:14
 * @description
 */
public class SpringBasicTest {

    /**
     * 最基础spring项目
     */
    @Test
    public void testBasic() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Student bean = applicationContext.getBean(Student.class);
        System.out.println(bean.getUsername());
    }

}
