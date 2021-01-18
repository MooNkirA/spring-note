package com.moon.spring.extenstion.registrar;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * 自定义类扫描器，继承Spring框架的ClassPathBeanDefinitionScanner
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-17 13:56
 * @description
 */
public class CustomBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

    public CustomBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    // 重写扫描的方法，即可以做相应的扩展，但此示例暂无做扩展
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        return super.doScan(basePackages);
    }

}
