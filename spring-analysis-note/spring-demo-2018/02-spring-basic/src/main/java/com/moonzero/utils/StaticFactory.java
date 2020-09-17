package com.moonzero.utils;

import com.moonzero.service.ICustomerService;
import com.moonzero.service.impl.CustomerServiceImpl;

/**
 * spring管理静态工厂--使用静态工厂的方法创建对象
 *
 * @author MoonZero
 */
public class StaticFactory {

    // 创建静态工厂方法，返回类的对象
    public static ICustomerService createCustomerService() {
        // 返回接口的现实类对象
        return new CustomerServiceImpl();
    }
}
