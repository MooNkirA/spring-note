package com.moon.springsample.publisher;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.ResolvableType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义事件发布器，只实现里面比较重要的方法
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

    public MyApplicationEventMulticaster() {
    }

    public MyApplicationEventMulticaster(ConfigurableApplicationContext context) {
        this.context = context;
    }

    /**
     * 收集监听器
     *
     * @param listenerBeanName 实例 bean 的名称
     */
    @Override
    public void addApplicationListenerBean(String listenerBeanName) {
        System.out.println("====>" + listenerBeanName);

    }

    /**
     * 发布事件
     *
     * @param event
     * @param eventType
     */
    @Override
    public void multicastEvent(ApplicationEvent event, ResolvableType eventType) {

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
