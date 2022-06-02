package com.moon.springsample.beans;

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
public class BeanNoSetter {

    private int a;
    private String b;
    private Date c;

    @Override
    public String toString() {
        return new StringJoiner(", ", BeanNoSetter.class.getSimpleName() + "[", "]")
                .add("a=" + a)
                .add("b='" + b + "'")
                .add("c=" + c)
                .toString();
    }

}
