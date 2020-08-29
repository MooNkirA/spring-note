package com.moon.springsample.config;

import com.moon.springsample.typefilter.SceneTypeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-27 22:52
 * @description
 */
@Configuration
// 添加排除属性excludeFilters，选择自定义规则（FilterType.CUSTOM），指定自定义过滤器的class
@ComponentScan(value = {"com.moon.springsample"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM, classes = SceneTypeFilter.class))
public class SpringConfiguration {
}
