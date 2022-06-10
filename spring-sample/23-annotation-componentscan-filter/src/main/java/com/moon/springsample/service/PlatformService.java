package com.moon.springsample.service;

/**
 * 运营平台的业务接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-27 23:24
 * @description
 */
public interface PlatformService {

    /**
     * 计算运营平台提成的数量
     *
     * @param userType 用户的类型
     */
    void calcSalePercentage(String userType);

}
