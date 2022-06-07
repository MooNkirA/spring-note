package com.moon.springmvc.beans;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.StringJoiner;

/**
 * 没有 setter 方法的 bean
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-01 15:59
 * @description
 */
@ToString
@Getter
public class BeanNoSetter {

    private int a;
    private String b;
    private Date c;

}
