package com.moon.springsample.bean;

import org.springframework.stereotype.Component;

/**
 * 一个普通的 bean，用于测试自定义的 BeanPostProcessor
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-16 14:35
 * @description
 */
@Component
public class OrdinaryBean {

    public OrdinaryBean() {
        System.out.println("OrdinaryBean 构造方法执行了...");
    }

}
