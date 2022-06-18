package com.moon.springsample.publisher;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.ResolvableType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义事件发布器，只实现里面比较重要的方法
 * <p>注：需要测试时放开配置类中的 @Bean 注解来创建此实例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-16 22:47
 * @description
 */
public class MyApplicationEventMulticaster implements ApplicationEventMulticaster {

    // 定义属性，存放收集到的监听器
    private List<GenericApplicationListener> listeners = new ArrayList<>();
    // Spring 容器
    private ConfigurableApplicationContext context;
    // 线程池
    private ThreadPoolTaskExecutor executor;

    public MyApplicationEventMulticaster() {
    }

    public MyApplicationEventMulticaster(ConfigurableApplicationContext context, ThreadPoolTaskExecutor executor) {
        this.context = context;
        this.executor = executor;
    }

    /**
     * 收集监听器，
     * 注意：此方法传入的都是通过实现 ApplicationListener 接口方式的监听器实例
     *
     * @param listenerBeanName 实例 bean 的名称
     */
    @Override
    public void addApplicationListenerBean(String listenerBeanName) {
        // 获取监听器实例
        ApplicationListener listener = context.getBean(listenerBeanName, ApplicationListener.class);
        // 获取该监听器支持的事件类型，即 ApplicationListener 接口的泛型。示例为了方便，直接取第一个元素
        ResolvableType type = ResolvableType.forClass(listener.getClass()).getInterfaces()[0].getGeneric();
        // 将原始的 ApplicationListener 封装为支持事件类型检查的 GenericApplicationListener
        GenericApplicationListener genericApplicationListener = new GenericApplicationListener() {
            // 是否支持某事件类型，方法参数的 ResolvableType 是真实的事件类型
            @Override
            public boolean supportsEventType(ResolvableType eventType) {
                // 判断真实的事件类型是否
                return type.isAssignableFrom(eventType);
            }

            // 事件的调用
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                executor.submit(() -> listener.onApplicationEvent(event));
            }
        };
        // 加入集合
        listeners.add(genericApplicationListener);
    }

    /**
     * 发布事件
     *
     * @param event
     * @param eventType
     */
    @Override
    public void multicastEvent(ApplicationEvent event, ResolvableType eventType) {
        // 循环所有监听器，调用 onApplicationEvent 方法
        for (GenericApplicationListener listener : listeners) {
            // 判断是否支持的事件类型
            if (listener.supportsEventType(ResolvableType.forClass(event.getClass()))) {
                listener.onApplicationEvent(event);
            }
        }
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {

    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {

    }

    @Override
    public void removeApplicationListenerBean(String listenerBeanName) {

    }

    @Override
    public void removeApplicationListeners(Predicate<ApplicationListener<?>> predicate) {

    }

    @Override
    public void removeApplicationListenerBeans(Predicate<String> predicate) {

    }

    @Override
    public void removeAllListeners() {

    }

    @Override
    public void multicastEvent(ApplicationEvent event) {

    }

}
