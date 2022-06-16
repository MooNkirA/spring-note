package com.moon.springsample.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义 Spring 事件对象，需要继承 ApplicationEvent
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-15 22:56
 * @description
 */
public class MyEvent extends ApplicationEvent {

    /**
     * 创建 ApplicationEvent 对象，后面两个参数是
     */
    public MyEvent(Object source) {
        super(source);
    }
}
