package com.moon.springsample.event;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * 事件源
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-30 17:48
 * @description
 */
@Component
// @DependsOn 代表当前类依赖于value属性中相关类的创建，当其相关类创建完成后，当前类再进行创建
@DependsOn("eventZListener")
public class EventSource {

    public EventSource() {
        System.out.println("事件源EventSource对象创建了");
    }

}
