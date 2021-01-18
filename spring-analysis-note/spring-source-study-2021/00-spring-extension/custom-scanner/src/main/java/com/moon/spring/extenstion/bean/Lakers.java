package com.moon.spring.extenstion.bean;

import lombok.Data;

import java.util.List;

/**
 * 实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-17 13:36
 * @description
 */
@Data
public class Lakers {

    private String Logo;
    private List<BasketballPlayer> teamMembers;

}
