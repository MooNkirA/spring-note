package com.moonzero.ui;

import com.moonzero.service.IUserService;
import com.moonzero.utils.BeanFactory;

/**
 * 模拟表现层
 *
 * @author MoonZero
 */
public class FactoryTest {
    public static void main(String[] args) {
        // 获取业务逻辑层对象
        IUserService us = (IUserService) BeanFactory.getBean("userService");
        // 调用业务层方法
        us.add("工厂模式解耦~~");
    }
}
