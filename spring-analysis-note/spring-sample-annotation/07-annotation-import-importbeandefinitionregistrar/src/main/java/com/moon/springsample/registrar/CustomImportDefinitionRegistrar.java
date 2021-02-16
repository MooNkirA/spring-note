package com.moon.springsample.registrar;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AspectJTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 自定义 bean 定义注册器，实现 ImportBeanDefinitionRegistrar 接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-29 21:33
 * @description
 */
/* 注：实现ImportBeanDefinitionRegistrar接口的类，必须配合@Import注解导入的方式，接口的方法才会被spring所调用 */
public class CustomImportDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    // 定义ASPECTJ的表达式变量，注：这里不能通过@Value注解来赋值
    private String expression;

    // 定义变量，保存配置文件所指定的扫描的包路径
    private String customPackage;

    /* 默认无参构造方法，用于读取配置文件，给表达式赋值 */
    public CustomImportDefinitionRegistrar() {
        try {
            // 1. 使用spring框架的工具类，获取配置文件的properties对象
            Properties properties = PropertiesLoaderUtils.loadAllProperties("customImport.properties");
            // 2. 读取配置值，给expression属性赋值
            this.expression = properties.getProperty("custom.importselector.expression");
            // 3. 读取配置指定的扫描包路径
            this.customPackage = properties.getProperty("custom.importselector.package");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 此方法无返回值，需要在方法中手动注册bean到注册中心容器中
     *
     * @param importingClassMetadata 使用@Import注解的类上所有的注解信息，
     *                               此示例即SpringConfiguration类上所有注解信息
     * @param registry               BeanDefinition注册中心
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 1. 定义扫描包的名称字符串集合
        List<String> basePackages = null;

        /*
         * 2. 判断有 @Import 注解的类上是否有 @ComponentScan 注解
         *   这里的主要逻辑是如果配置了 @ComponentScan 注解并指定了扫描的包，则以此配置值为要扫描的包
         */
        if (importingClassMetadata.hasAnnotation(ComponentScan.class.getName())) {
            // 3. 取出@ComponentScan注解的属性值（value/basePackages）
            Map<String, Object> attributes = importingClassMetadata
                    .getAnnotationAttributes(ComponentScan.class.getName());
            // 4. 根据key获取basePackages的属性值（注：这里的”basePackages“解析什值包含了”value“的值）
            basePackages = new ArrayList<>(Arrays.asList((String[]) attributes.get("basePackages")));
        }

        /*
         * 5. 判断是否有此属性，或者是否指定了包扫描的信息
         *  如果没有@ComponentScan注解则属性值为null，如果有@ComponentScan注解但未指定value/basePackages值，则basePackages默认为空数组
         *  这里主要处理的逻辑就是，如果@ComponentScan没有配置包扫描路径，设置默认扫描当前@Import注解所在的包路径
         */
        if (basePackages == null || basePackages.isEmpty()) {
            // 定义变量接收标识了@Import注解的类所在的包路径
            String basePackage = null;
            // 6. 获取标识了@Import注解类所在包的名称
            try {
                basePackage = Class.forName(importingClassMetadata.getClassName()).getPackage().getName();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            // 7. 保存到 basePackages 信息中
            basePackages = new ArrayList<>();
            basePackages.add(basePackage);
        }

        // 判断配置文件中是否指定包扫描的路径
        if (!StringUtils.isEmpty(this.customPackage)) {
            // 有配置，则加到需要扫描的包路径集合中
            basePackages.add(this.customPackage);
        }

        /*
         * 8. 创建类路径扫描器。（这里的扫描器与自定义 ImportSelector 的使用的不一样）
         *   参数registry：是spring框架的BeanDefinitionRegistry注册中心
         *   参数useDefaultFilters：代表是否使用默认的扫描规则。本例使用aspectJ扫描规则，故设置为false
         */
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry, false);
        // 9. 创建类型过滤器(此处使用aspectJ切入点表达式类型过滤器)
        TypeFilter typeFilter = new AspectJTypeFilter(expression, this.getClass().getClassLoader());
        // 10. 将创建好的类型过滤器添加到类路径扫描器中
        scanner.addIncludeFilter(typeFilter);

        // 11. 扫描指定包
        scanner.scan(basePackages.toArray(new String[basePackages.size()]));
    }

}
