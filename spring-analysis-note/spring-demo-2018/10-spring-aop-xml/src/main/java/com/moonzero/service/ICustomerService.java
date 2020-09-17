package com.moonzero.service;

/**
 * 客户业务层接口
 *
 * @author MoonZero
 */
public interface ICustomerService {

    /**
     * 保存客户
     */
    void saveCustomer();

    /**
     * 更新客户
     *
     * @param i
     */
    void updateCustomer(int i);
}
