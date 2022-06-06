package com.moon.springmvc.beans;

import java.util.Date;
import java.util.StringJoiner;

/**
 * 标准的 bean
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-01 15:58
 * @description
 */
public class NormalBean {

    private int a;
    private String b;
    private Date c;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public Date getC() {
        return c;
    }

    public void setC(Date c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NormalBean.class.getSimpleName() + "[", "]")
                .add("a=" + a)
                .add("b='" + b + "'")
                .add("c=" + c)
                .toString();
    }
}
