package com.moon.spring.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 循环依赖示例Bean - 构造函数方式注入
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-5-28 23:31
 * @description
 */
@Data
// @Component
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
