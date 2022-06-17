package com.moon.springsample.listener;

import com.moon.springsample.annotations.CustomListener;
import com.moon.springsample.event.MyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 基于自定义的注解 @CustomListener 模拟实现的事件监听器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-15 13:01
 * @description
 */
@Component
public class MyCustomEventListener {

    private static final Logger log = LoggerFactory.getLogger(MyCustomEventListener.class);

    /*
     * 模拟使用自定义注解 @CustomListener 标识文件，模拟实现事件监听
     */
    @CustomListener
    public void listenEvent(Object event) {
        if (event instanceof MyEvent) {
            MyEvent myEvent = (MyEvent) event;
            log.debug("基于自定义注解模拟实现的事件监听器。获取事件数据：{}", myEvent);
        }
    }

}
