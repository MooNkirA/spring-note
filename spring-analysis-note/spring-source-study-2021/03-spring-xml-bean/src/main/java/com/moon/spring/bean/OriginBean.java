package com.moon.spring.bean;


/**
 * 用于测试 <bean> 子标签 <replaced-method>
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-3 22:10
 * @description
 */
public class OriginBean {

    // 如果这个方法需要进行业务功能增强，但是又不希望在原来基础上修改，可以用replaced-method标签
    public void replaceMethod(String param) {
        System.out.println("I am origin method! param = " + param);
    }

    public void replaceMethod(int param) {
        System.out.println("I am origin method! param = " + param);
    }

}
