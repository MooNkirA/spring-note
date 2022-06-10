package com.moon.spring.test;

import com.moon.spring.bean.Bird;
import com.moon.spring.bean.Cat;
import com.moon.spring.bean.MotherCat;
import com.moon.spring.util.ContextUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * Spring @Configuration 注解测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-20 21:36
 * @description
 */
public class ConfigurationTest {

    private final ApplicationContext context = ContextUtils.getAnnotationContext();

    @Test
    public void testConfigurationFeature() {
        Cat cat = context.getBean(Cat.class);
        System.out.println("Cat实例hashCode: " + cat.hashCode());

        MotherCat motherCat = context.getBean(MotherCat.class);
        System.out.println("MotherCat实例hashCode: " + motherCat.getCat().hashCode());

        Bird bird = context.getBean(Bird.class);
        System.out.println("Bird实例hashCode: " + bird.hashCode());
    }


}
