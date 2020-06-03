package com.moon.spring.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 测试多例Bean的循环依赖
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-6-2 23:14
 * @description
 */
@Data
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CircularRefPropertyB {

    /* 多例情况下的循环依赖也是不允许，会报错 */
    @Autowired
    CircularRefPropertyA circularRefPropertyA;

}
