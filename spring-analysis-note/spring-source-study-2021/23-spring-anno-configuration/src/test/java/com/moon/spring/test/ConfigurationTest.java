package com.moon.spring.test;

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
        System.out.println(cat.hashCode());

        MotherCat motherCat = context.getBean(MotherCat.class);
        System.out.println(motherCat.getCat().hashCode());
    }


}
