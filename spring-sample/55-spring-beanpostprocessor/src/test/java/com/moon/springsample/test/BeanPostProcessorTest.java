package com.moon.springsample.test;

import com.moon.springsample.bean.Cat;
import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.processor.order.Bpp1;
import com.moon.springsample.processor.order.Bpp2;
import com.moon.springsample.processor.order.Bpp3;
import com.moon.springsample.processor.order.Bpp4;
import com.moon.springsample.processor.order.Bpp5;
import com.moon.springsample.processor.order.Bpp6;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;

import java.util.Arrays;
import java.util.List;

/**
 * BeanPostProcessor 实现测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-16 15:40
 * @description
 */
public class BeanPostProcessorTest {

    /* 基础 BeanPostProcessor 接口实现测试 */
    @Test
    public void TestBasicBeanPostProcessor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        System.out.println(context.getBean(Cat.class));
    }

    /* 测试 BeanPostProcessor 接口实现的加载排序 */
    @Test
    public void TestBeanPostProcessorOrder() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        List<BeanPostProcessor> list = Arrays.asList(new Bpp1(), new Bpp2(), new Bpp3(), new Bpp4(), new Bpp5(), new Bpp6());
        // 使用 Spring 的 dependencyComparator 排序器
        list.sort(beanFactory.getDependencyComparator());

        list.forEach(processor -> processor.postProcessBeforeInitialization(new Object(), ""));
    }

}
