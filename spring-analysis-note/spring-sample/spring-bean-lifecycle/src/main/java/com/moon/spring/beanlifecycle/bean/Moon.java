package com.moon.spring.beanlifecycle.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author MoonZero
 * @version 1.0
 * @date 2019-9-28 10:16
 * @description
 */
public class Moon implements InitializingBean, DisposableBean {
    public Moon() {
        System.out.println("调用无参构造器创建Moon");
    }

    @Override
    public void destroy() {
        System.out.println("销毁Moon");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("初始化Moon");
    }

}
