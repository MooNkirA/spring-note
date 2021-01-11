package com.moon.spring.test;

import com.moon.spring.bean.ConstructorArgBean;
import com.moon.spring.bean.DecoratorBean;
import com.moon.spring.bean.FactoryBean;
import com.moon.spring.bean.OriginBean;
import com.moon.spring.bean.People;
import com.moon.spring.bean.PropertyBean;
import com.moon.spring.bean.Son;
import com.moon.spring.bean.Woman;
import com.moon.spring.service.PeopleService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于xml配置文件的bean标签的测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-3 12:40
 * @description
 */
public class XmlBeanTest {

    private ApplicationContext context;

    @Before
    public void init() {
        // 读取spring类路径下的配置文件
        context = new ClassPathXmlApplicationContext("spring.xml");
    }

    /**
     * 测试<bean>标签中的abstract与parent属性
     */
    @Test
    public void testAbstractAndParentAttribute() {
        // Parent parent = context.getBean("parent", Parent.class); // 配置了abstract属性，无法实例化
        Son son = context.getBean("son", Son.class);
        System.out.println("Son实例::" + son.toString());  // Son实例::Son(username=parent name, age=18)
    }

    /**
     * 测试<bean>标签中的 init-method 与 destroy-method 属性
     */
    @Test
    public void testInitMethodAndDestroyMethod() {
        // 容器初始化时，InitDestroyBean就初始化，无参构造方法执行，然后执行init-method方法
        ClassPathXmlApplicationContext ac = (ClassPathXmlApplicationContext) context;
        // 关闭容器，观察单例对象的销毁前的destroy-method方法
        ac.close();
        /*
         * 输出结果：
         * 16:38:07.643 [main] INFO  com.moon.spring.bean.InitDestroyBean - ********* InitDestroyBean类的无参构造方法执行了 *********
         * 16:38:07.643 [main] INFO  com.moon.spring.bean.InitDestroyBean - ********* InitDestroyBean.initMehtod() 初始化方法执行了 *********
         * 16:38:07.659 [main] DEBUG o.s.c.s.ClassPathXmlApplicationContext - Closing org.springframework.context.support.ClassPathXmlApplicationContext@337d0578, started on Sun Jan 03 16:38:07 CST 2021
         * 16:38:07.674 [main] INFO  com.moon.spring.bean.InitDestroyBean - ********* InitDestroyBean.destroyMethod() 销毁方法执行了 *********
         */
    }

    /**
     * 测试<bean>标签中的 factory-bean 与 factory-method 属性
     */
    @Test
    public void testFactoryBeanAndFactoryMethod() {
        FactoryBean factoryBean = context.getBean("factoryBean", FactoryBean.class);
        System.out.println(factoryBean.toString());
    }

    /**
     * 测试 <bean> 子标签 <lookup-method>
     */
    @Test
    public void testLookupMethod() {
        // lookup-method配置抽象类PeopleService，在容器中的实例是代理对象
        PeopleService peopleService = context.getBean("peopleService", PeopleService.class);
        System.out.println(peopleService); // com.moon.spring.service.PeopleService$$EnhancerBySpringCGLIB$$54ac3cfe@70e8f8e
        People people = peopleService.getPeople();
        System.out.println(people instanceof Woman); // true
        peopleService.showGender(); // I am woman
    }

    /**
     * 测试 <bean> 子标签 <replaced-method>
     */
    @Test
    public void testReplacedMethod() {
        OriginBean bean = context.getBean("originBean", OriginBean.class);
        System.out.println(bean); // com.moon.spring.bean.OriginBean$$EnhancerBySpringCGLIB$$cd7d5e12@70e8f8e
        // 调用被替换的方法
        bean.replaceMethod("MooN"); // I am replace method --> reimplement! params = MooN
        // 调用其他没有替换的方法
        bean.replaceMethod(1); // I am origin method! param = 1
    }

    /**
     * 测试 <bean> 子标签 <constructor-arg>
     */
    @Test
    public void testConstructorArg() {
        ConstructorArgBean bean = context.getBean("constructorArgBean", ConstructorArgBean.class);
        System.out.println(bean); // ConstructorArgBean(username=moon, password=123)
    }

    /**
     * 测试 <bean> 子标签 <property>
     */
    @Test
    public void testProperty() {
        PropertyBean bean = context.getBean("propertyBean", PropertyBean.class);
        System.out.println(bean); // PropertyBean(username=MoonZero, password=123)
    }

    /**
     * 测试使用p/c空间名称去封装装饰bean
     */
    @Test
    public void testPAndCNameSpace() {
        DecoratorBean bean = context.getBean("decoratorBean", DecoratorBean.class);
        System.out.println(bean); // DecoratorBean(username=Moon, password=123, age=12, sex=1)
    }

}
