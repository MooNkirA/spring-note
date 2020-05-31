package com.moon.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 循环依赖示例Bean
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-5-28 23:31
 * @description
 */
public class CircularRefA {

    @Autowired
    private CircularRefB circularRefB;

    public CircularRefA() {
        System.out.println("============CircularRefA()=========== 自动注入属性circularRefB:" + circularRefB);
    }


}
