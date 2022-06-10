package com.moon.springsample.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 用户信息的实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-13 17:36
 * @description
 */
@Data
public class Userinfo implements Serializable {

    private Integer id;
    private byte[] images;
    private String description;

}
