package com.moon.spring.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 循环依赖示例Bean - 构造函数方式注入
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-7 22:54
 * @description
 */
// @Component
@Getter
@Setter
public class CircularRefConstructorA {

    /**
     * 有参构造函数的循环依赖会报错
     *
     * @param cb
     */
    @Autowired
    public CircularRefConstructorA(CircularRefConstructorB cb) {
        System.out.println("============CircularRefConstructorA构造函数触发=========== 构造函数方式自动注入CircularRefConstructorB:" + cb);
    }

}
