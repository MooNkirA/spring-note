package com.moon.springsample.service;

/**
 * 账号业务接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-1 16:55
 * @description
 */
public interface AccountService {

    /**
     * 转账
     *
     * @param sourceName 转出账户名称
     * @param targetName 转入账户名称
     * @param money      转账金额
     */
    void transfer(String sourceName, String targetName, Double money);

}
