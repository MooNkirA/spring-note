package com.moon.spring.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * spring标准事件监听类，监听ContextRefreshedEvent事件，容器完成加载成功后发布此事件
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-16 15:35
 * @description
 */
@Component
public class SpringRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("========SpringRefreshedListener容器加载完成了=========");
    }

}

