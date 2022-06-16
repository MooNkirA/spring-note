package com.moon.springsample.listener;

import com.moon.springsample.event.MyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 基于 ApplicationListener 接口实现的事件监听器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-15 12:55
 * @description
 */
@Component
public class MyApplicationListener implements ApplicationListener<MyEvent> {

    private static final Logger log = LoggerFactory.getLogger(MyApplicationListener.class);

    /**
     * 监听事件，当前有事件发布时，执行此方法
     *
     * @param event 事件发送的信息
     */
    @Override
    public void onApplicationEvent(MyEvent event) {
        log.debug("基于 ApplicationListener 接口实现的事件监听器。获取事件数据：{}", event);
    }
}
