package com.moon.spring.listener;

import org.springframework.context.ApplicationListener;
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
public class ContextStoppedListener implements ApplicationListener<ContextStoppedEvent> {

    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        System.out.println("========ContextStoppedListener上下文停止事件监听========");
    }

}
