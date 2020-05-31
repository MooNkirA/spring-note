package com.moon.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 循环依赖示例Bean
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-5-28 23:32
 * @description
 */
public class CircularRefB {

    @Autowired
    private CircularRefA circularRefA;

    public CircularRefB() {
        System.out.println("============CircularRefB()无参构造函数触发=========== 自动注入属性circularRefA:" + circularRefA);
    }

}
