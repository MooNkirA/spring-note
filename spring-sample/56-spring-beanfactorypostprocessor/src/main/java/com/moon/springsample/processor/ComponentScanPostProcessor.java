package com.moon.springsample.processor;

import com.moon.springsample.config.SpringConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * BeanFactoryPostProcessor 后置处理器模拟实现 @ComponentScan 功能
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-17 14:07
 * @description
 */
public class ComponentScanPostProcessor implements BeanDefinitionRegistryPostProcessor {

    /**
     * 所有的 Bean 都已被加载，但还没有 Bean 被实例化时执行此方法，
     * 允许重写或添加属性
     *
     * @param beanFactory the bean factory used by the application context
     * @throws BeansException in case of errors
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    /**
     * 所有常规的 Bean 定义将被加载，但还没有 Bean 被实例化时执行此方法，
     * 允许在下一个后处理阶段启动之前进一步添加Bean定义。
     * TODO: 此方法比 postProcessBeanFactory 先执行，后面分析源码找原因
     *
     * @param registry the bean definition registry used by the application context
     * @throws BeansException in case of errors
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            // 使用 Spring 的工具类 AnnotationUtils 获取指定的类上注解。此处获取配置类上的 @ComponentScan 注解
            // TODO: 这里是写死特定配置类，应该是读取容器中所有配置，日后有时间优化
            ComponentScan componentScan = AnnotationUtils.findAnnotation(SpringConfiguration.class, ComponentScan.class);

            if (componentScan != null) {
                // 获取 @ComponentScan 注解上配置的包路径
                for (String p : componentScan.basePackages()) {
                    // 需要将包形式转换成路径形式，如: com.moon.springsample.component -> classpath*:com/moon/springsample/component/**/*.class
                    String path = "classpath*:" + p.replace(".", "/") + "/**/*.class";
                    System.out.println(p + " --> " + path);

                    // 创建 Spring 操作元数据的工具类 CachingMetadataReaderFactory
                    CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
                    // 使用 Spring 的获取资源文件的工具类，解析配置扫描的路径下所有 class 文件，并封装成 Resource 对象
                    Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
                    // Bean 名称生成器
                    AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
                    for (Resource resource : resources) {
                        // 获取被扫描类的元数据，解析元数据是基于 ASM 技术
                        MetadataReader reader = factory.getMetadataReader(resource);
                        // 获取初始扫描类的注解元数据
                        AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
                        // 判断类上是否标识了 @Component 及其衍生的注解（如：@Controller、@Serivce 等）
                        if (annotationMetadata.hasAnnotation(Component.class.getName())
                                || annotationMetadata.hasMetaAnnotation(Component.class.getName())) {
                            // 创建 BeanDefinition 实例
                            AbstractBeanDefinition bd = BeanDefinitionBuilder
                                    .genericBeanDefinition(reader.getClassMetadata().getClassName())
                                    .getBeanDefinition();
                            // 生成 bean 名称
                            String name = generator.generateBeanName(bd, registry);
                            // 注册 BeanDefinition
                            registry.registerBeanDefinition(name, bd);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
