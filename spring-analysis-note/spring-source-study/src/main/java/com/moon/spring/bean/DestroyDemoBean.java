package com.moon.spring.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * 实现 DisposableBean 接口，测试Bean的销毁
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-6-7 13:46
 * @description
 */
@Component
public class DestroyDemoBean implements DisposableBean {

    /* 在Bean实例销毁时，会执行到destroy()方法的逻辑 */
    @Override
    public void destroy() throws Exception {
        System.out.println("+++++DisposableBean接口的实现，DestroyDemoBean实例的destroy()方法执行+++++");
    }
}
