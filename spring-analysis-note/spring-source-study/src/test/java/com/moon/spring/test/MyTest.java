package com.moon.spring.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.moon.spring.bean.CustomAnnotationClass;
import com.moon.spring.bean.Student;
import com.moon.spring.beanDefinition.BeanClass;
import com.moon.spring.config.ComponentScanConfig;

import redis.clients.jedis.Jedis;

/**
 * 测试类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-12-15 12:25
 * @description
 */
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = {"classpath:spring.xml"}) // 测试注解方式配置spring时注释掉
public class MyTest {

    private static final String BASE_PACKAGE = "com.moon.spring";

    @Autowired
    private ApplicationContext applicationContext;

    /* 类路径获取配置文件上下文对象（ClassPathXmlApplicationContext） */
    @Test
    public void ClassPathXmlApplicationContextTest() {
        // 读取spring类路径下的配置文件
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student.getUserName());

        /* 扩展知识：修改allowBeanDefinitionOverriding与allowCircularReferences属性（此做法无意义） */
        /*applicationContext.setAllowBeanDefinitionOverriding(true);
        applicationContext.setAllowBeanDefinitionOverriding(true);
        // 设置后需要调用refresh方法，此方法是springcontext加载中核心方法，必须执行
        applicationContext.refresh();*/
    }

    /* 文件系统路径获取配置文件【绝对路径】上下文对象（FileSystemXmlApplicationContext）【基本上不用】 */
    @Test
    public void FileSystemXmlApplicationContextTest() {
        // 读取spring的配置文件，需要绝对路径
        FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("D:\\code\\spring-note\\spring-analysis-note\\spring-source-study\\src\\main\\resources\\spring.xml");
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student.getUserName());
    }

    /* 无配置文件加载容器上下文对象（AnnotationConfigApplicationContext） */
    @Test
    public void AnnotationConfigApplicationContextTest() {
        // 注解扫描上下文对象
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student.getUserName());
    }

    @Test
    public void componentScanTest() {
        applicationContext = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        System.out.println("@ComponentScan Test --> " + applicationContext.getBean("userServiceImpl"));
    }

    /* springboot 加载容器的上下文对象（EmbeddedWebApplicationContext） */
    @Test
    public void EmbeddedWebApplicationContextTest() {
        // springboot在启动的时候就会用到此上下文对象，启动spring容器，创建一个嵌入式的tomcat
        /*ApplicationContext applicationContext = (ApplicationContext) new EmbeddedWebApplicationContext();
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student.getUserName());*/
    }

    @Test
    public void testBeanClass() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        BeanClass beanClass = (BeanClass) ac.getBean("beanClass");
        System.out.println("BeanClass -->" + beanClass);
    }

    /* 自定义注解DI注入 */
    @Autowired
    private CustomAnnotationClass customAnnotationClass;

    /* 测试自定义注解注册到spring容器与DI自动注入 */
    @Test
    public void customAnnotationRegistryTest() {
        System.out.println("自定义注解注册到spring容器成功 ---> DI依赖注入获取对象 ---> " + customAnnotationClass.getName());
    }

    /* ======= 自定义标签解析测试 ========== */
    @Autowired
    private Jedis jedis;

    @Test
    public void customTagTest() {
        jedis.set("moon", "this is a custom tag");
        System.out.println(jedis.get("moon"));
    }

    /* 测试多例情况下循环依赖时会出现报错 */
    @Test
    public void circularRefPropertyTest() {
        // 注：因为多例情况，spring容器启动时是不会创建实例，所以这里需要手动调用getBean()方法，此时才会去创建实例
        applicationContext.getBean("circularRefPropertyA");
    }

}
