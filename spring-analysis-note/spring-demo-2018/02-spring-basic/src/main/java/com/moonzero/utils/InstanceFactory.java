package com.moonzero.utils;

import com.moonzero.service.ICustomerService;
import com.moonzero.service.impl.CustomerServiceImpl;

/**
 * spring管理实例工厂—使用实例工厂的方法创建对象
 *
 * @author MoonZero
 */
public class InstanceFactory {
    // 创建非静态的获取实例对象方法
    public ICustomerService createCustomerService() {
        return new CustomerServiceImpl();
    }
}
