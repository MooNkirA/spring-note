package com.moon.spring.test;

import com.moon.spring.bean.Bird;
import com.moon.spring.bean.Cat;
import com.moon.spring.config.AppConfig;
import com.moon.spring.util.ContextUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * Spring @Import 注解测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-15 23:06
 * @description
 */
public class ImportTest {

    private final ApplicationContext context = ContextUtils.getAnnotationContext(AppConfig.class);

    @Test
    public void testImportBasic() {
        Cat cat = context.getBean(Cat.class);
        Bird bird = context.getBean(Bird.class);
        System.out.println(cat);
        System.out.println(bird);
    }

}
