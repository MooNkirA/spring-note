package com.moon.spring.extenstion.registrar;

import com.moon.spring.extenstion.annotation.BeansScanner;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义 bean 注册器，实现 ImportBeanDefinitionRegistrar 接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-17 14:02
 * @description
 */
public class BeansScannerRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 注册 BeanDefinition 的处理逻辑
     *
     * @param importingClassMetadata 注解元数据
     * @param registry               BeanDefinition 注册中心
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 是否扫描所有类标识
        boolean scanAllBeans = true;

        // 获取自定义的扫描器注解
        AnnotationAttributes annotationAttributes = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(BeansScanner.class.getName()));

        // 创建自定义包扫描器（其实没有做扩展）
        CustomBeanDefinitionScanner scanner = new CustomBeanDefinitionScanner(registry);

        // 判断自定义BeansScaner注解annotationClass属性
        Class<? extends Annotation> clazz = annotationAttributes.getClass("annotationClass");
        // 判断是否设置扫描指定的注解，如有，则设置全部类扫描标识为false
        if (!Annotation.class.equals(clazz)) {
            scanAllBeans = false;
            scanner.addIncludeFilter(new AnnotationTypeFilter(clazz));
        }

        // 分别获取value与basePackages属性，收集所有配置的包路径
        List<String> basePackages = new ArrayList<>();
        for (String pkg : annotationAttributes.getStringArray("value")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (String pkg : annotationAttributes.getStringArray("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }

        // 判断是否整个包扫描
        if (scanAllBeans) {
            // 方法的入参TypeFilter是函数式接口，直接使用lambda表达，直接返回true就是扫描所有的类
            scanner.addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
        }

        // 扫描包
        scanner.doScan(StringUtils.toStringArray(basePackages));
    }

}
