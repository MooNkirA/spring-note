package com.moon.springsample.processor;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;

/**
 * BeanFactoryPostProcessor 后置处理器模拟扫描 mybatis mapper 接口生成代理实例功能
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-17 16:51
 * @description
 */
public class MapperScanPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            // 创建 Spring 的获取资源文件的工具类 PathMatchingResourcePatternResolver
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            // 解析配置扫描的路径下所有 class 文件，并封装成 Resource 对象
            // TODO: 这里是写死路径，应该是动态获取，日后有时间优化
            Resource[] resources = resolver.getResources("classpath:com/moon/springsample/mapper/**/*.class");

            // 创建 Spring 操作元数据的工具类 CachingMetadataReaderFactory
            CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
            // Bean 名称生成器
            AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
            for (Resource resource : resources) {
                // 获取资源元数据
                MetadataReader metadataReader = factory.getMetadataReader(resource);
                // 获取类元数据
                ClassMetadata classMetadata = metadataReader.getClassMetadata();
                // 判断是否为接口
                if (classMetadata.isInterface() && metadataReader.getAnnotationMetadata().hasAnnotation(Mapper.class.getName())) {
                    // 接口名称
                    String className = classMetadata.getClassName();
                    // Mapper 接口被 Spring 管理的本质：实际被创建为 MapperFactoryBean 注册到容器中
                    AbstractBeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition(MapperFactoryBean.class)
                            .addConstructorArgValue(className)
                            .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                            .getBeanDefinition();
                    /*
                     * 生成 bean 名称。
                     * 这里需要注意：如果直接使用上面的 MapperFactoryBean 的 BeanDefinition 生成名称，
                     * 此时第个 mapper 接口的名称都是一样，最终多个 mapper 接口只会注册一个到容器中（因为保存 bean 的名称都一样）
                     * 参考 Mybatis 整合到 Spring 的源码，使用当前 mapper 接口来创建一个临时的 BeanDefinition，只用于生成 bean 的名称，并不会去实例化
                     */
                    AbstractBeanDefinition tempBd = BeanDefinitionBuilder.genericBeanDefinition(className).getBeanDefinition();
                    String beanName = generator.generateBeanName(tempBd, registry);
                    // 注册
                    registry.registerBeanDefinition(beanName, bd);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }
}
