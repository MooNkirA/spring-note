package com.moon.spring.listener;

import com.moon.spring.event.CustomEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 自定义spring事件监听器，需实现spring框架的 ApplicationListener 接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-16 14:45
 * @description
 */
// 实现ApplicationListener接口可以指定事件的类型，也可以不指定。
// @Component // 如果要容器加载后直接发布事件被监听到，需要使用@Component等注解将当前类注册到spring容器中
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("自定义事件CustomEvent发布，内容是：" + event.getContent());
    }

}

// public class CustomEventListener implements ApplicationListener {
//     @Override
//     public void onApplicationEvent(ApplicationEvent event) {
//         /*
//          * 如果不指定监听的事件类型，此时方法的入参是spring的事件类ApplicationEvent
//          * 可以在方法进行类型的判断，再执行相应的逻辑
//          */
//         if (event instanceof CustomEvent) {
//             // do something...
//         }
//     }
// }
