package com.moon.springannotation.test;

import com.moon.springsample.utils.LogUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解 @Lazy 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-31 22:38
 * @description
 */
public class SpringLazyTest {

    @Test
    public void dependsOnBasicTest() {
        // 1. 创建注解扫描的容器
        ApplicationContext context = new AnnotationConfigApplicationContext("com.moon.springsample.config");
        // 2. 获取容器中的bean对象
        LogUtil logUtil = context.getBean("logUtil", LogUtil.class);
        // 3. 执行方法（断点查看是否执行到此方法时才执行构造函数里的内容）
        logUtil.printLog();

        // 再次获取bean对象，判断是否是单例
        LogUtil logUtil2 = context.getBean("logUtil", LogUtil.class);
        System.out.println(logUtil == logUtil2);
    }

}
