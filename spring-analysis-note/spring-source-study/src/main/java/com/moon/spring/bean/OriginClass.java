package com.moon.spring.bean;

import java.util.List;

public class OriginClass {

    /*
     * 如果这个方法需要进行业务功能增强，但是又不希望在原来基础上修改，可以用replaced-method标签
     */
    public void replaceMethod(String param) {
        System.out.println("I am origin method! param = " + param);
    }

    public void replaceMethod(List param) {
        System.out.println("I am origin method! param = " + param);
    }

}
