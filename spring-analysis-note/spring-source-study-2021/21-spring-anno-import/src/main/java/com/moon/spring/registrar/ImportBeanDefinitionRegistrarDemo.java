package com.moon.spring.registrar;

import com.moon.spring.annotation.CustomAnnotation;
import com.moon.spring.bean.Student;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Optional;

/**
 * ImportBeanDefinitionRegistrar 接口实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-16 10:40
 * @description
 */
public class ImportBeanDefinitionRegistrarDemo implements ImportBeanDefinitionRegistrar {
    /**
     * 此方法无返回值，需要在方法中手动注册bean到注册中心容器中
     *
     * @param importingClassMetadata 使用@Import注解的类上所有的注解信息，
     *                               此示例即SpringConfiguration类上所有注解信息
     * @param registry               BeanDefinition注册中心
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry,
                                        BeanNameGenerator importBeanNameGenerator) {
        System.out.println("ImportBeanDefinitionRegistrarDemo.registerBeanDefinitions() 方法执行了....");

        /* importingClassMetadata 一样可以获取导入类的所有注解信息 */
        MergedAnnotations annotations = importingClassMetadata.getAnnotations();

        // 需要手动将实例化类加入到registry注册中心
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(Student.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("username", "ImportBeanDefinitionRegistrar接口修改的名称"));
        beanDefinition.setPropertyValues(propertyValues);
        String beanName = importBeanNameGenerator.generateBeanName(beanDefinition, registry);
        System.out.println("BeanNameGenerator类生成的BeanDefinition的名称是：" + beanName); // com.moon.spring.bean.Student
        // 注册BeanDefinition
        registry.registerBeanDefinition(beanName, beanDefinition);
    }
}
