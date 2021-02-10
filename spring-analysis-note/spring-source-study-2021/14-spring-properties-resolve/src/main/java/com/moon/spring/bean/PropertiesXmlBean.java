package com.moon.spring.bean;

import lombok.Data;

/**
 * 传统通过xml配置注入properties文件值
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-9 14:05
 * @description
 */
@Data
public class PropertiesXmlBean {

    private String name;
    private String password;

}
