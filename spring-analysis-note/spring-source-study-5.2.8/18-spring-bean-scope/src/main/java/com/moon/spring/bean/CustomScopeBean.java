package com.moon.spring.bean;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 测试自定义bean作用域
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-13 14:59
 * @description
 */
@Component
@Scope("MooNkirAScope")
public class CustomScopeBean {
}
