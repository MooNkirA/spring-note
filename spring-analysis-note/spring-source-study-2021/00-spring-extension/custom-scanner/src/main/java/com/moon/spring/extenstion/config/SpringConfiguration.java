package com.moon.spring.extenstion.config;

import com.moon.spring.extenstion.annotation.BeansScanner;

/**
 * Spring 项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-17 14:05
 * @description
 */
// 标识自定义扫描规则注解，指定要批量扫描的包路径
@BeansScanner("com.moon.spring.extenstion.bean")
public class SpringConfiguration {
}
