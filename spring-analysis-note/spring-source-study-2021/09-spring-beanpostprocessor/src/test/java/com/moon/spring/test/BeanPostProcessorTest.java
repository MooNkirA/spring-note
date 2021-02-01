package com.moon.spring.test;

import com.moon.spring.bean.PostProcessorBean;
import com.moon.spring.config.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * BeanPostProcessor 相关接口测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-31 11:27
 * @description
 */
public class BeanPostProcessorTest {

    private final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(SpringConfiguration.class);

    /*
     * 测试InstantiationAwareBeanPostProcessor
     */
    @Test
    public void testInstantiationAwareBeanPostProcessor() {
        PostProcessorBean postProcessorBean = context.getBean("postProcessorBean", PostProcessorBean.class);
        System.out.println(postProcessorBean.getComponentA());
        System.out.println(postProcessorBean.getComponentB());
    }

}
