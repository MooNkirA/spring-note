package com.moon.spring.postprocessor;

import com.moon.spring.annotation.CustomAnnotation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

/**
 * 自定义 BeanDefinitionRegistryPostProcessor 案例，实现扫描自定义注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-15 10:41
 * @description
 */
@Component
public class CustomAnnotationScanPostProcessor implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        /* ============ 自定义注解注册 ============ */
        // 1. 创建扫描器，将BeanDefinitionRegistry（注册器）传入
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        // 2. 将自定义注解添加注解过滤器中
        scanner.addIncludeFilter(new AnnotationTypeFilter(CustomAnnotation.class));
        /*
         * 3. 设置扫描的包路径
         *   注：1. 如不设置扫描包路径，则不会生效。
         *       2. 设置此扫描包路径，会与spring原生@Component及其衍生注解一起会扫描
         */
        scanner.scan("cn.moon.autumn");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
