package com.moon.springsample.test;

import com.moon.springsample.bean.Cat;
import com.moon.springsample.config.SpringConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;

/**
 * BeanFactory 接口测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-14 17:34
 * @description
 */
public class BeanFactoryTest {

    @Test
    public void test() {
        // 获取 BeanFactory 实现 DefaultListableBeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // BeanFactory 刚创建后，里面是没有任务实例对象
        printBeanDefinitionNames(beanFactory);

        // 创建 bean 的定义对象 BeanDefinition（用于封装待创建的类实例的 class, scope, 初始化, 销毁等信息）
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(SpringConfiguration.class)
                .setScope("singleton")
                .getBeanDefinition();
        // 注册 bean
        beanFactory.registerBeanDefinition("springConfiguration", beanDefinition);
        printBeanDefinitionNames(beanFactory);

        // 使用 AnnotationConfigUtils 工具类，给 BeanFactory 添加一些常用的后处理器，用于扩展功能
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        // BeanFactoryPostProcessor 后处理器。主要功能是补充了一些 bean 定义
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(beanFactoryPostProcessor -> {
            System.out.println("BeanFactoryPostProcessor 类型实例：" + beanFactoryPostProcessor);
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        });
        // BeanPostProcessor 后处理器, 针对 bean 的生命周期的各个阶段提供扩展, 例如 @Autowired @Resource ...
        beanFactory.getBeansOfType(BeanPostProcessor.class)
                .values()
                .stream()
                .sorted(beanFactory.getDependencyComparator())
                .forEach(beanPostProcessor -> {
                    System.out.println("BeanPostProcessor 类型实例：" + beanPostProcessor);
                    beanFactory.addBeanPostProcessor(beanPostProcessor);
                });
        System.out.println("======================= 分隔线 =====================");
        printBeanDefinitionNames(beanFactory);

        // beanFactory.preInstantiateSingletons(); // 准备好所有单例
    }

    private void printBeanDefinitionNames(ListableBeanFactory beanFactory) {
        for (String bdName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(bdName);
        }
        System.out.println("======================= 分隔线 =====================");
    }


}
