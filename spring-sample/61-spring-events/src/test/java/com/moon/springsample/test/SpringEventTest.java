package com.moon.springsample.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.event.MyEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring 事件机制测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-16 14:59
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class SpringEventTest {

    private static final Logger log = LoggerFactory.getLogger(SpringEventTest.class);

    // // 创建基于注解的 spring 容器
    // private final AnnotationConfigApplicationContext context =
    //         new AnnotationConfigApplicationContext("com.moon.springsample");

    // 注入 Spring 事件发布器
    @Autowired
    private ApplicationEventPublisher publisher;

    @Test
    public void test1() {
        log.debug("主线业务执行开始...");
        // 发送事件
        publisher.publishEvent(new MyEvent("{事件的数据}"));
        log.debug("主线业务执行结束...");
    }
}
