package com.moon.spring.bean;

import lombok.Data;

/**
 * 用于测试通过 BeanDefinitionRegistryPostProcessor 新增到容器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-13 22:45
 * @description
 */
@Data
public class BeanToAdd {

    private String value;

}
