package com.moon.springsample.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * 自定义 Spring 事件对象
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-15 12:26
 * @description
 */
@Getter
@Setter
@ToString
public class MyEvent extends ApplicationEvent {

    private final String code;
    private final String message;

    /**
     * 创建 ApplicationEvent 对象，后面两个参数是
     */
    public MyEvent(Object source, String code, String message) {
        super(source);
        this.code = code;
        this.message = message;
    }

}
