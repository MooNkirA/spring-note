package com.moon.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义Spring事件，需要继承Spring的 ApplicationEvent 事件类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-16 14:37
 * @description
 */
public class CustomEvent extends ApplicationEvent {

    private String content;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public CustomEvent(Object source, String content) {
        super(source);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
