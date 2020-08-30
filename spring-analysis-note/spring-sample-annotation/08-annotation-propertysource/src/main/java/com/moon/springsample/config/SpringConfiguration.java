package com.moon.springsample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-30 08:01
 * @description
 */
@Configuration
// 导入其他模块的配置类
@Import({JdbcConfig.class})
// 导入配置文件（支持类路径方式）
@PropertySource("classpath:jdbc.properties")

// 导入配置文件（支持文件路径方式）
// @PropertySource("file:///D:/code/spring-note/spring-analysis-note/spring-sample-annotation/08-annotation-propertysource/src/main/resources/jdbc.properties")

// 导入配置文件，如果存在其他属性，则路径属性value不能省略，ignoreResourceNotFound属性指定是否忽略资源文件不存在，默认是false，如果修改为true，如果找不到资源文件，也不会报错。
// @PropertySource(value = {"classpath:jdbc.properties"}, ignoreResourceNotFound = true)

// 导入配置文件，如果存在其他属性，则路径属性value不能省略，encoding属性指定解析资源文件使用的字符集。当有中文的时候，需要指定中文的字符集。
// @PropertySource(value = {"classpath:jdbc.properties"}, encoding = "UTF-8")

// 导入配置文件，也支持xml类型的文件（比较少用）
// @PropertySource({"classpath:jdbc.xml"})
public class SpringConfiguration {

    /*
     * 注意：spring 4.3版本以前，没有资源文件的工厂类factory这个属性，即无默认的文件解析器
     *  如果要解析资源文件，则需要手动注册资源文件解析器bean实例到ioc容器中
     *  以下方法是针对Spring 4.3版本之前的properties文件解析器创建。
     *  在4.3版本之后使用PropertySourceFactory接口的唯一实现类：DefaultPropertySourceFactory
     */
    /*@Bean
    public static PropertySourcesPlaceholderConfigurer createPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }*/

}
