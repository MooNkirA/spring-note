package com.moon.springsample.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * 账户实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-5 15:20
 * @description
 */
@Data
public class Account implements Serializable {
    
    private Integer id;
    private String name;
    private Double money;

}
