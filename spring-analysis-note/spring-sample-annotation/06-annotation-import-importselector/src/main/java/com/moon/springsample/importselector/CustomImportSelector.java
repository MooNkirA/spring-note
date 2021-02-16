package com.moon.springsample.importselector;

import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AspectJTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 自定义导入器，实现 ImportSelector 接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-29 14:06
 * @description
 */
/* 注：实现ImportSelector接口的类，必须配合@Import注解导入的方式，接口的方法才会被spring所调用 */
public class CustomImportSelector implements ImportSelector {

    // 定义ASPECTJ的表达式变量，注：这里不能通过@Value注解来赋值
    private String expression;

    // 定义变量，保存配置文件所指定的扫描的包路径
    private String customPackage;

    /*
     * 默认无参构造方法
     *  这里需要实现的逻辑是：用于读取配置文件，给表达式赋值
     */
    public CustomImportSelector() {
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
     * 此方法用于批量导入bean对象到ioc容器，所以需要实现获取要导入的bean全限定类名数组
     * 需求：
     * 导入的过滤规则是FilterType的ASPECTJ的类型
     *
     * @param importingClassMetadata 使用@Import注解的类上所有的注解信息，
     *                               此示例即SpringConfiguration类上所有注解信息
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
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
        if (basePackages == null || basePackages.size() == 0) {
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

        // 需求改造增加的逻辑：判断配置文件中是否指定包扫描的路径
        if (!StringUtils.isEmpty(this.customPackage)) {
            // 有配置，则加到需要扫描的包路径集合中
            basePackages.add(this.customPackage);
        }

        /*
         * 8. 创建类路径扫描器。 此构造函数传入参数是useDefaultFilters，代表是否使用默认的扫描规则。
         *  因为本示例中想使用aspectJ扫描规则，不想让将要注册的类添加@Component等注解，所以此参数设置为false，不使用默认扫描规则
         */
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        // 9. 创建类型过滤器(此处使用aspectJ切入点表达式类型过滤器)
        TypeFilter typeFilter = new AspectJTypeFilter(expression, CustomImportSelector.class.getClassLoader());
        // 10. 将创建好的类型过滤器添加到类路径扫描器中
        scanner.addIncludeFilter(typeFilter);

        // 11. 定义用于存放扫描结果的类全限定名称集合
        Set<String> classes = new HashSet<>();
        // 12. 循环包路径数组，使用扫描器挨个扫描包下的类，获取类的全限定名称
        for (String basePackage : basePackages) {
            // 将扫描到每个类的全限定名称添加到set集合中
            scanner.findCandidateComponents(basePackage).forEach(beanDefinition -> classes.add(beanDefinition.getBeanClassName()));
        }

        // 13. 按方法的返回值类型，返回全限定类名的数组
        return classes.toArray(new String[classes.size()]);
    }

}
