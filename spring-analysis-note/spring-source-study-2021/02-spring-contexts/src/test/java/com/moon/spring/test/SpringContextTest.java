package com.moon.spring.test;

import com.moon.spring.bean.BeanWithAnnotation;
import com.moon.spring.common.bean.Student;
import org.junit.Test;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Spring 各种类型的上下文对象测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-12-26 12:26
 * @description
 */
public class SpringContextTest {

    private static final String BASE_PACKAGE = "com.moon.spring";

    /* 类路径获取配置文件上下文对象（ClassPathXmlApplicationContext） */
    @Test
    public void testClassPathXmlApplicationContext() {
        // 读取spring类路径下的配置文件
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student.getUsername());

        /* 扩展知识：修改allowBeanDefinitionOverriding与allowCircularReferences属性（此做法无意义） */
        // applicationContext.setAllowBeanDefinitionOverriding(true);
        // applicationContext.setAllowBeanDefinitionOverriding(true);
        // 设置后需要调用refresh方法，此方法是springcontext加载中核心方法，必须执行
        // applicationContext.refresh();
    }

    /* 文件系统路径获取配置文件【绝对路径】上下文对象（FileSystemXmlApplicationContext）【基本上不用】 */
    @Test
    public void testFileSystemXmlApplicationContext() {
        // 读取spring的配置文件，需要绝对路径
        FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("D:\\code\\spring-note\\spring-analysis-note\\spring-source-study-2021\\02-spring-contexts\\src\\main\\resources\\spring.xml");
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student.getUsername());
    }

    /* 无配置文件加载容器上下文对象（AnnotationConfigApplicationContext） */
    @Test
    public void testAnnotationConfigApplicationContext() {
        // 注解扫描上下文对象
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        BeanWithAnnotation bean = (BeanWithAnnotation) applicationContext.getBean("beanWithAnnotation");
        System.out.println(bean.getData());
    }

    /* springboot 加载容器的上下文对象（EmbeddedWebApplicationContext） */
    @Test
    public void testEmbeddedWebApplicationContext() {
        // springboot在启动的时候就会用到此上下文对象，启动spring容器，创建一个嵌入式的tomcat
        new EmbeddedWebApplicationContext();
    }

}
