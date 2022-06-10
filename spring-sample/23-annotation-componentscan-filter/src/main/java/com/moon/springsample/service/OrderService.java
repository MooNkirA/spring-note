package com.moon.springsample.service;

/**
 * 用户下单业务接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-27 23:19
 * @description
 */
public interface OrderService {

    /**
     * 计算下单优惠的金额
     *
     * @param userType 用户的类型
     */
    void calcOrderDiscount(String userType);

}
