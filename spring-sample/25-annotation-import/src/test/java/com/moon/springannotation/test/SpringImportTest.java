package com.moon.springannotation.test;

import com.moon.springsample.config.JdbcConfig;
import com.moon.springsample.config.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * Sprring @Import 注解的使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-29 10:38
 * @description
 */
public class SpringImportTest {

    @Test
    public void importAnnotaionBasicTest() {
        /*
         * 1. 创建注解扫描的容器，这里只扫描项目总配置类SpringConfiguration，
         *   所以抽取出来的其他模块的配置类，就算标识@Configuration,@Component等注解，也不会被spring扫描加载
         *   此时就需要使用 @Import 注解，将其他模块的配置类引入到项目总配置类中
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2. 测试使用 @Import 注解引入的配置类是否加载到ioc容器中，可以根据对象的类型去获取
        // JdbcConfig jdbcConfig = context.getBean(JdbcConfig.class);
        // 如果想通过ioc容器中bean的名称去获取，则要了解，通过@Import注解引入的类，其命名规则是以类的全限定名称来作为ioc容器中的唯一标识
        JdbcConfig jdbcConfig = context.getBean("com.moon.springsample.config.JdbcConfig", JdbcConfig.class);
        // 输出结果
        System.out.println(jdbcConfig);

        // 3. 获取@Bean注解存入的配置类中的对象实例
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        // 输出结果
        System.out.println(dataSource);
    }

    /* 获取spring ioc容器中所有注册的bean的名称 */
    @Test
    public void getBeanDefinitionNamesTest() {
        // 创建注解扫描的容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 调用getBeanDefinitionNames()方法，获取所有注册到容器中bean的名称（唯一标识），返回值是字符串数组
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        // 循环输入所有名称
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

}
