package com.moon.springsample.listener;

import com.moon.springsample.event.MyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 基于 @EventListener 注解实现的事件监听器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-15 13:01
 * @description
 */
@Component
public class MyEventListener {

    private static final Logger log = LoggerFactory.getLogger(MyEventListener.class);

    /*
     * 使用 @EventListener 标识的方法，并指定监听的事件类型，可以指定监听多个事件
     * 当有该类型的事件发布，此方法会被执行。（方法的名称随意即可）
     */
    @EventListener({MyEvent.class, ContextRefreshedEvent.class})
    public void listenEvent(Object event) {
        if (event instanceof MyEvent) {
            MyEvent myEvent = (MyEvent) event;
            log.debug("基于 @EventListener 注解实现的事件监听器。获取事件数据：{}", myEvent);
        }
    }

}
