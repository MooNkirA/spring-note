package com.moon.spring.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 循环依赖示例Bean
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-5-28 23:32
 * @description
 */
@Data
@Component
public class CircularRefC {

    @Autowired
    private CircularRefA circularRefA;

    public CircularRefC() {
        System.out.println("============CircularRefC()无参构造函数触发=========== 自动注入属性circularRefA:" + circularRefA);
    }

}