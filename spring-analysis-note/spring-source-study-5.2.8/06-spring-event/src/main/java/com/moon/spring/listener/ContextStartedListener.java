package com.moon.spring.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

/**
 * spring标准事件监听类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-16 15:32
 * @description
 */
@Component
public class ContextStartedListener implements ApplicationListener<ContextStartedEvent> {

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        System.out.println("========ContextStartedListener上下文开始事件监听========");
    }

}
