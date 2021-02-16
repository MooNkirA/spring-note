package com.moon.spring.aware;

import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ImportAware 接口运用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-5 10:16
 * @description
 */
public class CustomImportAware implements ImportAware {

    /**
     * Set the annotation metadata of the importing @{@code Configuration} class.
     * <p>
     * 设置导入类的注解元数据。
     * 即通过实现此接口的类，在相关的配置类中使用@Import注解导入时，可以获取到其配置类上相关所有的注解元数据
     *
     * @param importMetadata 注解元数据
     */
    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        System.out.println("CustomImportAware.setImportMetadata()方法执行了....");

        /* 获取使用@Import注解导入的类的所有注解元信息 */
        MergedAnnotations annotations = importMetadata.getAnnotations();
        if (importMetadata.hasAnnotation(PropertySource.class.getName())) {
            MergedAnnotation<PropertySource> propertySource = annotations.get(PropertySource.class);
            String[] values = propertySource.getStringArray("value");
            for (String s : values) {
                System.out.println("SpringConfiguration类上的@PropertySource注解的value值是：" + s);
            }
        }
    }

}
