package com.moon.spring.test;

import com.moon.spring.bean.PropertySourceBean;
import com.moon.spring.config.SpringConfiguration;
import com.moon.spring.util.ContextUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * Spring @PropertySource 注解测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-14 16:49
 * @description
 */
public class PropertySourceTest {

    private final ApplicationContext context = ContextUtils.getAnnotationContext(SpringConfiguration.class);

    @Test
    public void testPropertySourceBasic() {
        PropertySourceBean bean = context.getBean("propertySourceBean", PropertySourceBean.class);
        System.out.println(bean);
    }

}
