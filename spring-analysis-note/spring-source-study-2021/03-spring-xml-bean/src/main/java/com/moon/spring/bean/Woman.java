package com.moon.spring.bean;

/**
 * 用于测试 <bean> 子标签 <lookup-method> 使用
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-3 18:21
 * @description
 */
public class Woman implements People {

    @Override
    public void showGender() {
        System.out.println("I am woman");
    }

}
