package com.moon.spring.beanlifecycle;

import com.moon.spring.beanlifecycle.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring Framework 框架 bean 生命周期学习测试专用工程
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-8-15 16:51
 * @description 测试spring框架中bean的生命周期
 */
@SpringBootApplication(scanBasePackages = {"com.moon.spring.beanlifecycle"})
public class BeanLifeCycleApp {

    public static void main(String[] args) {
        SpringApplication.run(BeanLifeCycleApp.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        System.out.println("容器创建完毕");
        context.close();
    }

}
