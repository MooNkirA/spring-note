package com.moon.springmvc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-30 15:40
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;
    private int age;

}
