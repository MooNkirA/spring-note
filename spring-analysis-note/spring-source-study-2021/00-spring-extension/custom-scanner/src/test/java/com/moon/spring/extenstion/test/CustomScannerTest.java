package com.moon.spring.extenstion.test;

import com.moon.spring.extenstion.config.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 自定义扫描器测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-17 16:09
 * @description
 */
public class CustomScannerTest {

    @Test
    public void testBeansScanner() {
        // 加载spring配置类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 输出容器中所有实例
        for (String beanName : context.getBeanDefinitionNames()) {
            Object bean = context.getBean(beanName);
            System.out.println(bean);
        }
    }

}
