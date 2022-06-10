package com.moon.spring.test;

import com.moon.spring.bean.Fish;
import com.moon.spring.component.AutowiredConstructorComponent;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Spring @Autowired 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-24 17:16
 * @description
 */
public class AutowiredTest {

    private final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext("com.moon.spring");

    @Test
    public void testAutowiredConstructor() {
        Map<String, AutowiredConstructorComponent> beansOfType = context.getBeansOfType(AutowiredConstructorComponent.class);
        for (Map.Entry<String, AutowiredConstructorComponent> entry : beansOfType.entrySet()) {
            System.out.println(entry.getKey() + " :: " + entry.getValue());
        }
    }

}
