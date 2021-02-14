package com.moon.spring.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 测试使用@PropertySource导入配置文件，解析占位符
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-13 21:53
 * @description
 */
@Component
@Data
public class PlaceholderBean {

    @Value("${moon.name}")
    private String name;
    @Value("${moon.password}")
    private String password;

}
