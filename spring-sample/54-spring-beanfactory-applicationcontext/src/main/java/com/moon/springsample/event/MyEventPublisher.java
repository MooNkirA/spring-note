package com.moon.springsample.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * 实现 ApplicationEventPublisherAware 接口，用于获取
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-15 12:40
 * @description
 */
@Component
public class MyEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    /**
     * 获取 ApplicationEventPublisher 实例
     *
     * @param applicationEventPublisher
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    // 定义方法，发送事件
    public void doEventPublish(String code, String message) {
        publisher.publishEvent(new MyEvent(this, code, message));
    }
}
