package com.moon.springsample.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件源
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-16 14:05
 * @description
 */
public class MyApplicationEvent extends ApplicationEvent {

    // 定义属性接收事件源
    private final Object source;

    /* 因为父类定义有参构造方法，所以子类必须提供有参构造 */
    public MyApplicationEvent(Object source) {
        super(source);
        this.source = source;
    }

    @Override
    public Object getSource() {
        return source;
    }

}
