package com.moon.springsample.event;

import org.springframework.stereotype.Component;

/**
 * 事件监听器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-30 17:51
 * @description
 */
@Component
// 注：这里故意将名称中间多插入一个无关的"Z"字母，为了让这个类按字母排序时比较先后，以便观察使用@DependsOn注解后调整创建顺序的效果
public class EventZListener {

    public EventZListener() {
        System.out.println("事件监听器EventZListener对象创建了");
    }

}
