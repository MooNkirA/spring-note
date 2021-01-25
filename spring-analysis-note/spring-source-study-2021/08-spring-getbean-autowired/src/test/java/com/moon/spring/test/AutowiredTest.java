package com.moon.spring.test;

import com.moon.spring.component.AutowiredConstructorComponent;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

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
