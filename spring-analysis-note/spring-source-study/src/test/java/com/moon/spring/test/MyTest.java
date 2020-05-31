package com.moon.spring.test;

import com.moon.spring.bean.ConstructorArgBean;
import com.moon.spring.bean.CustomAnnotationClass;
import com.moon.spring.bean.OriginClass;
import com.moon.spring.bean.PropertyClass;
import com.moon.spring.bean.ShowSexClass;
import com.moon.spring.bean.Student;
import com.moon.spring.beanDefinition.BeanClass;
import com.moon.spring.factorybean.FactoryBeanDemo;
import com.moon.spring.factorybean.FactoryBeanOther;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

/**
 * 测试类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-12-15 12:25
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class MyTest {

    private static final String BASE_PACKAGE = "com.moon.spring";

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PropertyClass propertyClass;

    @Autowired
    private ShowSexClass showSexClass;

    @Autowired
    private OriginClass originClass;

    @Autowired
    private ConstructorArgBean constructorArgBean;

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
        ApplicationContext ac = new AnnotationConfigApplicationContext("com.moon.spring");
        BeanClass beanClass = (BeanClass) ac.getBean("beanClass");
        System.out.println("BeanClass -->" + beanClass);
    }


    /* 测试lookup-method子标签 */
    @Test
    public void lookUpMethod() {
        showSexClass.getPeople().showSex();
    }

    /* 测试replaced-method子标签 */
    @Test
    public void replacedMethod() {
        originClass.replaceMethod("xx");
        originClass.replaceMethod(new ArrayList());
    }

    /* 测试constructor-arg子标签 */
    @Test
    public void constructorArg() {
        System.out.println(constructorArgBean);
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

    /* FactoryBean接口实现测试 */
    @Test
    public void factoryBeanTest() {
        // 实现了FactoryBean接口的类，通过bean的id只能获取该类实现了getObject()方法返回的对象实例
        FactoryBeanOther other = (FactoryBeanOther) applicationContext.getBean("factoryBeanDemo");
        System.out.println(other); // com.moon.spring.factorybean.FactoryBeanOther@4cc8eb05

        // 如果要获取实现了FactoryBean接口的类的实例，只能通过【“&” + bean的id】来获取实例
        FactoryBeanDemo factoryBeanDemo = (FactoryBeanDemo) applicationContext.getBean("&factoryBeanDemo");
        System.out.println(factoryBeanDemo); // com.moon.spring.factorybean.FactoryBeanDemo@51f116b8
    }

}
