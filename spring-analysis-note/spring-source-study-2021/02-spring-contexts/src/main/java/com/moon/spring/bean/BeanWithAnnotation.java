package com.moon.spring.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 带有spring创建对象注解的实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-12-26 12:56
 * @description
 */
@Data
@Component
public class BeanWithAnnotation {

    private String data = "I am N";

}
