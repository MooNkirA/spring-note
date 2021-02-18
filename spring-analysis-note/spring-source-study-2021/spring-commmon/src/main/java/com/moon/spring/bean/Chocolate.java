package com.moon.spring.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-17 09:02
 * @description
 */
@Data
public class Chocolate {

    private String name;
    private String color;
    private BigDecimal weight;
    private List<String> materials;

}
