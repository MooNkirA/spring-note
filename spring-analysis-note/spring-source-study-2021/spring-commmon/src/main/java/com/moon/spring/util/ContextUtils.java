package com.moon.spring.util;

import com.moon.spring.constant.Constants;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring 上下文工具类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-12 21:58
 * @description
 */
public class ContextUtils {

    public static AnnotationConfigApplicationContext getAnnotationContext() {
        return getAnnotationContext(Constants.BASE_PACKAGES);
    }

    public static AnnotationConfigApplicationContext getAnnotationContext(String... basePackages) {
        return new AnnotationConfigApplicationContext(basePackages);
    }

    public static AnnotationConfigApplicationContext getAnnotationContext(Class<?>... componentClasses) {
        return new AnnotationConfigApplicationContext(componentClasses);
    }

    public static ClassPathXmlApplicationContext getXmlContext(String configLocation) {
        return new ClassPathXmlApplicationContext(configLocation);
    }

    private ContextUtils() {
    }
}
