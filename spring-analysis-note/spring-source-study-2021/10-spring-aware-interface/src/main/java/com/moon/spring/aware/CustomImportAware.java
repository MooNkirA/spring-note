package com.moon.spring.aware;

import org.springframework.context.annotation.ImportAware;
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
        System.out.println(importMetadata);
    }

}
