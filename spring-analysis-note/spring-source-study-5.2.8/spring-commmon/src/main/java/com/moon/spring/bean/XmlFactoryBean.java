package com.moon.spring.bean;

import lombok.Data;

/**
 * 用于测试 xml 配置文件中的 factory-method 与 factory-bean
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-3 17:08
 * @description
 */
@Data
public class XmlFactoryBean {

    private String name = "I am XmlFactoryBean";

}
