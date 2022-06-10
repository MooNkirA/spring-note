package com.moon.springsample.config;

import com.moon.springsample.custom.CustomBeanNameGenerator;
import com.moon.springsample.service.UserService;
import com.moon.springsample.service.impl.UserServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-31 23:33
 * @description
 */
@Configuration /* 标识当前类为配置类 */
/* 配置开启包扫描，不写扫描的包路径，则默认扫描当前@ComponentScan注解的类所在的包及其下的所有子包 */
// @ComponentScan

/* 配置开启包扫描，配置value属性，如果没有配置，则可以省略不写value="xxxx" */
// @ComponentScan("com.moon.springsample")

/* 配置开启包扫描，配置basePackages属性，效果与value一样，但不能与value属性同时存在 */
// @ComponentScan(basePackages = {"com.moon.springsample"})

/* 配置开启包扫描，指定具体要扫描的类的字节码，spring会扫描指定字节码的类所在的包及其子包下的所有类 */
// @ComponentScan(basePackageClasses = UserService.class)

/* 配置bean对象存入容器时自定义的命名规则 */
// @ComponentScan(basePackages = {"com.moon.springsample"}, nameGenerator = CustomBeanNameGenerator.class)

// resourcePattern属性用于指定符合组件检测条件的类文件，默认是包扫描下的**/*.class（即当前包及其子包下的任意名称的class文件）
// 如下示例，修改"*/*.class"后，代表只扫描当前包下的任意名称的class文件
// @ComponentScan(basePackages = {"com.moon.springsample"}, resourcePattern = "*/*.class")

/* includeFilters用于指定自定义组件扫描的过滤规则，表示包含某些规则，不会排除其他的规则 */
// @ComponentScan(basePackages = {"com.moon.springsample"}, includeFilters = @ComponentScan.Filter(value = Service.class))
/* excludeFilters用于指定组件扫描的排除规则，排除后不会加入到spring容器 */
@ComponentScan(basePackages = {"com.moon.springsample"}, excludeFilters = @ComponentScan.Filter(value = Service.class))
public class SpringConfiguration {
}
