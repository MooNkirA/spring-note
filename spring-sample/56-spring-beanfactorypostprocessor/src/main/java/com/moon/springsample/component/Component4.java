package com.moon.springsample.component;

import lombok.Getter;
import lombok.Setter;

/**
 * 测试 bean
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-17 10:36
 * @description
 */
@Getter
@Setter
public class Component4 {

    private Component5 component5;

    public Component4(Component5 component5) {
        if (component5 == null) {
            throw new RuntimeException("Component4 创建 component5 不能为空");
        }
        this.component5 = component5;
        System.out.println("Component4 加载到 Spring 容器中");
    }

}
