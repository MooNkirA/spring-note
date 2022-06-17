package com.moon.springsample.config;

import com.moon.springsample.annotations.CustomListener;
import com.moon.springsample.publisher.MyApplicationEventMulticaster;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-16 15:09
 * @description
 */
@Configuration
@ComponentScan("com.moon.springsample")
public class SpringConfiguration {

    @Bean
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        return executor;
    }

    // 创建 ApplicationEventMulticaster 实现，如果 Spring 容器存在此接口实现，则会自动实现异步的事件监听
    @Bean
    public SimpleApplicationEventMulticaster applicationEventMulticaster(ThreadPoolTaskExecutor executor) {
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        multicaster.setTaskExecutor(executor);
        return multicaster;
    }

    // 创建 SmartInitializingSingleton 实例，该接口的 afterSingletonsInstantiated 方法是在容器的所有 bean 都实例化完成后执行的回调
    @Bean
    public SmartInitializingSingleton smartInitializingSingleton(ConfigurableApplicationContext context) {
        return () -> {
            // 循环所有 bean 的名称
            for (String name : context.getBeanDefinitionNames()) {
                Object bean = context.getBean(name);
                for (Method method : bean.getClass().getMethods()) {
                    // 判断方法上是否标识了自定义的注解
                    if (method.isAnnotationPresent(CustomListener.class)) {
                        // 创建 ApplicationListener 实现，在接口的方法中反射调用注解标识的方法即可
                        context.addApplicationListener((event) -> {
                            Class<?> eventType = method.getParameterTypes()[0];// 监听器方法需要的事件类型
                            if (eventType.isAssignableFrom(event.getClass())) {
                                try {
                                    method.invoke(bean, event);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        };
    }

    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster(ConfigurableApplicationContext context, ThreadPoolTaskExecutor executor) {
        return new MyApplicationEventMulticaster(context);
    }
}
