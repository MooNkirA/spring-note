package com.moon.spring.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Spring @PropertySource 注解测试素材
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-14 16:45
 * @description
 */
@Component
@Data
public class PropertySourceBean {

    @Value("${moon.name}")
    private String name;
    @Value("${moon.password}")
    private String pwd;
    @Value("${moon.color}")
    private String color;

}
