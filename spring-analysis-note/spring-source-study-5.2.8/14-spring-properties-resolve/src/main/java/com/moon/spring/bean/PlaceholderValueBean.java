package com.moon.spring.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 注解 @Value 占位符处理测试素材
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-12 11:06
 * @description
 */
@Data
@Component
public class PlaceholderValueBean {

    @Value("${moon.name}")
    private String name;
    @Value("${moon.password}")
    private String password;

}
