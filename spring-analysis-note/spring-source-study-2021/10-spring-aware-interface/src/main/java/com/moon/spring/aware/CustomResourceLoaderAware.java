package com.moon.spring.aware;

import com.moon.spring.config.SpringConfiguration;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

/**
 * ResourceLoaderAware 接口运用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-11 18:18
 * @description
 */
@Component
public class CustomResourceLoaderAware implements ResourceLoaderAware {

    // 此对象可以理解为文件流
    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("CustomResourceLoaderAware.setResourceLoader()方法执行了....");
        this.resourceLoader = resourceLoader;
    }

    /* 通过@Bean注解，创建占位符解析器 */
    @Bean
    public PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        // 通过ResourceLoader对象读取配置文件，并设置到PropertySourcesPlaceholderConfigurer占位符解析器的location属性
        propertySourcesPlaceholderConfigurer.setLocation(resourceLoader.getResource("application.properties"));
        return propertySourcesPlaceholderConfigurer;
    }

    /**
     * 通过ResourceLoader对象，获取类的
     */
    public void getMetadata() {
        // 创建缓存MetadataReaderFactory对象，每个“ .class”文件，缓存一个MetadataReader实例。
        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory(this.resourceLoader);

        try {
            /* 测试读取SpringConfiguration类的Metadata对象 */
            MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(SpringConfiguration.class.getName());

            /* 获取类注解的Metadata元数据 */
            AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
            MergedAnnotations annotations = annotationMetadata.getAnnotations();
            // 判断是否有指定的注解
            if (annotationMetadata.hasAnnotation(PropertySource.class.getName())) {
                MergedAnnotation<PropertySource> propertySourceMergedAnnotation = annotations.get(PropertySource.class);
                // 获取指定的属性值
                String[] propertySourceValues = propertySourceMergedAnnotation.getStringArray("value");
                System.out.println("@PropertySource注解的value值：" + Arrays.asList(propertySourceValues));
                // 将注解所有属性转成map
                AnnotationAttributes propertySourceAttributes = propertySourceMergedAnnotation.asAnnotationAttributes(MergedAnnotation.Adapt.ANNOTATION_TO_MAP);
                System.out.println("@PropertySource注解：" + propertySourceAttributes);
            }
            if (annotationMetadata.hasAnnotation(Import.class.getName())) {
                MergedAnnotation<Import> importMergedAnnotation = annotations.get(Import.class);
                // 获取指定的属性值
                String[] importValues = importMergedAnnotation.getStringArray("value");
                System.out.println("@Import注解的value值：" + Arrays.asList(importValues));
                // 将注解所有属性转成map
                AnnotationAttributes importAttributes = importMergedAnnotation.asAnnotationAttributes(MergedAnnotation.Adapt.ANNOTATION_TO_MAP);
                System.out.println("@Import注解：" + importAttributes);
            }
            if (annotationMetadata.hasAnnotation(Configuration.class.getName())) {
                MergedAnnotation<Configuration> configurationMergedAnnotation = annotations.get(Configuration.class);
                // 将注解所有属性转成map
                AnnotationAttributes configurationAttributes = configurationMergedAnnotation.asAnnotationAttributes(MergedAnnotation.Adapt.ANNOTATION_TO_MAP);
                System.out.println("@Configuration注解：" + configurationAttributes);
            }

            /* 获取类的Metadata元数据 */
            ClassMetadata classMetadata = metadataReader.getClassMetadata();
            System.out.println("ClassName: " + classMetadata.getClassName());
            System.out.println("是否接口: " + classMetadata.isInterface());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
