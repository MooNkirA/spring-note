package com.moon.spring.bean;

import com.moon.spring.annotation.MoonConditionalOnBean;
import com.moon.spring.annotation.MoonConditionalOnClass;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 测试@Conditional注解的素材
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-17 16:50
 * @description
 */
@Component
// @MoonConditionalOnBean(Dog.class)
@MoonConditionalOnClass(Dog.class)
@Data
public class ConditionalBean {
}
