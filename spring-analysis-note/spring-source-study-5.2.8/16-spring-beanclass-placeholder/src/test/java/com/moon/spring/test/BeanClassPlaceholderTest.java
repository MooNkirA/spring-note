package com.moon.spring.test;

import com.moon.spring.bean.Bird;
import com.moon.spring.bean.Cat;
import com.moon.spring.bean.Fish;
import com.moon.spring.config.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * BeanClass属性占位符测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-11 19:48
 * @description
 */
public class BeanClassPlaceholderTest {

    private final ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

    /* 测试spring对beanClass属性占位符赋值 */
    @Test
    public void testBeanClassByPlaceHolder() {
        Cat cat = context.getBean(Cat.class);
        System.out.println(cat);
        Bird bird = context.getBean(Bird.class);
        System.out.println(bird);
        Fish fish = context.getBean(Fish.class);
        System.out.println(fish);
    }

}
