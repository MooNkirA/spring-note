package com.moon.spring.registrar;

import com.moon.spring.annotation.CustomAnnotation;
import com.moon.spring.bean.Son;
import com.moon.spring.bean.Parent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Optional;

/**
 * ImportSelector接口实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-16 09:32
 * @description
 */
public class ImportSelectorDemo implements ImportSelector {

    /**
     * 此方法用于批量导入bean对象到ioc容器
     *
     * @param importingClassMetadata 导入类上相关注解信息，此示例是获取到AppConfig类上的所有注解
     * @return 需要注册到ioc容器的bean的全限定名数组
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("ImportSelectorDemo.selectImports() 方法执行了....");

        /* 获取导入类的所有注解信息 */
        MergedAnnotations annotations = importingClassMetadata.getAnnotations();
        // 获取@CustomAnnotation自定义注解数据
        if (importingClassMetadata.hasAnnotation(CustomAnnotation.class.getName())) {
            MergedAnnotation<CustomAnnotation> customAnnotation = annotations.get(CustomAnnotation.class);
            Optional<String[]> value = customAnnotation.getValue("value", String[].class);
            value.ifPresent(v -> {
                for (String s : v) {
                    System.out.println("CustomAnnotation注解的value值：" + s);
                }
            });
        }

        // 获取@ComponentScan注解数据
        if (importingClassMetadata.hasAnnotation(ComponentScan.class.getName())) {
            Optional<String[]> value = annotations.get(ComponentScan.class).getValue("value", String[].class);
            value.ifPresent(v -> {
                for (String s : v) {
                    System.out.println("ComponentScan注解的value值：" + s);
                }
            });
        }

        // 返回需要实例化类的全限定名数组
        return new String[]{Parent.class.getName(), Son.class.getName()};
    }

}
