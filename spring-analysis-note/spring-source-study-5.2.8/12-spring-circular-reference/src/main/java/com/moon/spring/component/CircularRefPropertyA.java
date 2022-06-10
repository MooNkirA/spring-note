package com.moon.spring.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 测试多例Bean的循环依赖
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-7 22:56
 * @description
 */
@Getter
@Setter
// @Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CircularRefPropertyA {

    /* 多例情况下的循环依赖也是不允许，会报错 */
    @Autowired
    CircularRefPropertyB circularRefPropertyB;

}
