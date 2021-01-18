package com.moon.spring.test;

import com.moon.spring.event.CustomEvent;
import com.moon.spring.listener.CustomEventListener;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring 框架的事件测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-16 13:58
 * @description
 */
public class SpringEventTest {

    /* 事件发布与监听测试 */
    @Test
    public void testPublishEvent() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.moon.spring");
        /*
         * 如果容器加载完成后直接手动发布事件，则相应的事件监听类必须要使用xml配置或者@Component等注解注册到spring容器中
         *  如监听类没有使用@Component注解，则此次发布的事件无法监听到
         */
        context.publishEvent(new CustomEvent(context, "我是自定义事件！"));

        // 如果容器启动时没有将监听类注册到spring容器，可以在启动容器后手动设置监听类，然后再发布事件
        context.addApplicationListener(new CustomEventListener());
        context.publishEvent(new CustomEvent("可以传入任何值", "我是手动设置监听后，再发布的自定义事件！！"));

        // spring框架提供的标准事件
        context.start(); // 调用当前方法，spring会发布ContextStartedEvent事件
        context.stop(); // 调用当前方法，spring会发布ContextStoppedEvent事件

        /*
         * 输出结果如下：
         *  ========SpringRefreshedListener容器加载完成了=========
         *  自定义事件CustomEvent发布，内容是：我是手动设置监听后，再发布的自定义事件！！
         *  ========ContextStartedListener上下文开始事件监听========
         *  ========ContextStoppedListener上下文停止事件监听========
         */
    }

}
