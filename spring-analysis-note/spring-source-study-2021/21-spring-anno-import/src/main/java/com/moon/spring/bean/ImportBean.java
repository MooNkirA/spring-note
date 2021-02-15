package com.moon.spring.bean;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * Spring @Import 注解测试素材
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-15 22:35
 * @description
 */
@Component
// 使用@Import注解导入一个（或多个）类，导入的类不需要再使用@Component相关注解即可
@Import({Bird.class, Cat.class})
public class ImportBean {
}
