package com.moon.spring.test;

import com.moon.spring.bean.Dog;
import com.moon.spring.config.SpringConfiguration;
import com.moon.spring.util.ContextUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * Spring @ComponentScan 注解测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-14 17:16
 * @description
 */
public class ComponentScanTest {

    private final ApplicationContext context = ContextUtils.getAnnotationContext(SpringConfiguration.class);

    @Test
    public void testComponentScanBasic() {
        Dog bean = context.getBean("dog", Dog.class);
        System.out.println(bean);
    }

}
