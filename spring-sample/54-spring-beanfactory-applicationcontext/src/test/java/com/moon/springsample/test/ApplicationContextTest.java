package com.moon.springsample.test;

import com.moon.springsample.SpringSampleApp;
import com.moon.springsample.bean.Cat;
import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.config.WebConfig;
import com.moon.springsample.event.MyEventPublisher;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Locale;

/**
 * ApplicationContext 接口扩展功能测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-14 17:34
 * @description
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationContextTest {

    /* ApplicationContext 实现类 ClassPathXmlApplicationContext 测试 */
    @Test
    public void testClassPathXmlApplicationContext() {
        // 较为经典的容器, 基于 classpath 下 xml 格式的配置文件来创建容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(context.getBean(Cat.class));
    }

    /* ApplicationContext 实现类 FileSystemXmlApplicationContext 测试 */
    @Test
    public void testFileSystemXmlApplicationContext() {
        // 基于磁盘路径(绝对或者相对路径)下 xml 格式的配置文件来创建容器
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("src\\main\\resources\\applicationContext.xml");
        System.out.println(context.getBean(Cat.class));
    }

    /* ApplicationContext 实现类 AnnotationConfigApplicationContext 测试 */
    @Test
    public void testAnnotationConfigApplicationContext() {
        // 较为经典的容器, 基于 java 配置类来创建容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println("BeanDefinitionName: " + name);
        }
    }

    // TODO: 目前启动报错，Unable to start web server
    /* ApplicationContext 实现类 AnnotationConfigServletWebServerApplicationContext 测试 */
    @Test
    public void testAnnotationConfigServletWebServerApplicationContext() {
        // 较为经典的容器, 基于 java 配置类来创建, 用于 web 环境，在 Spring Boot 中应用
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println("BeanDefinitionName: " + name);
        }
    }

    /* ApplicationContext 国际化功能测试 */
    @Test
    public void testMessageSource() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        /*
         * String getMessage(String code, @Nullable Object[] args, Locale locale)
         * 	获取国际化信息
         *      code 参数：国际化资源中的属性名称
         *      args 参数：用于传递格式化串占位符所用的运行参数
         *      locale 参数：本地化对象
         */
        System.out.println(context.getMessage("hi", null, Locale.CHINA)); // CHINA 对应：messages_zh.properties
        System.out.println(context.getMessage("hi", null, Locale.ENGLISH)); // ENGLISH 对应：messages_en.properties
        System.out.println(context.getMessage("hi", null, Locale.JAPANESE)); // JAPANESE 对应：messages_ja.properties
    }

    /* ApplicationContext 访问资源功能测试 */
    @Test
    public void testGetResources() throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        /*
         * Resource[] getResources(String locationPattern) throws IOException;
         *  获取项目的资源文件，Spring 将其封装成 Resource 对象
         *      locationPattern 参数：资源文件的路径，如果访问依赖的 jar 包的资源目录，使用 classpath*:xxx 即可
         */
        Resource[] resources = context.getResources("classpath*:META-INF/spring.factories");
        for (Resource resource : resources) {
            System.out.println(resource);
        }
    }

    /* ApplicationContext 获取环境信息功能测试 */
    @Test
    public void testEnvironment() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        /*
         * Resource[] getResources(String locationPattern) throws IOException;
         *  获取项目的资源文件，Spring 将其封装成 Resource 对象
         *      locationPattern 参数：资源文件的路径，如果访问依赖的 jar 包的资源目录，使用 classpath*:xxx 即可
         */
        Environment environment = context.getEnvironment();
        System.out.println(environment);
        System.out.println("java_home: " + environment.getProperty("java_home"));
    }

    /* ApplicationContext 发布事件功能测试 */
    @Test
    public void testEvent() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 调用自定义的事件发布业务类，发送
        MyEventPublisher myEventPublisher = context.getBean(MyEventPublisher.class);
        myEventPublisher.doEventPublish("1", "这是一个事件消息");
    }

}
