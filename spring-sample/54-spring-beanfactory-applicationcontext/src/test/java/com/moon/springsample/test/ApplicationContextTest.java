package com.moon.springsample.test;

import com.moon.springsample.config.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class ApplicationContextTest {

    /* 注入 ApplicationContext */
    @Autowired
    private ApplicationContext context;

    /* ApplicationContext 国际化功能测试 */
    @Test
    public void testMessageSource() {
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

}
