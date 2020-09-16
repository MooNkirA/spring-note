package com.moon.springsample.service;

/**
 * 账户的业务层接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-15 22:42
 * @description
 */
public interface AccountService {

    /**
     * 转账
     *
     * @param sourceName
     * @param targetName
     * @param money
     */
    void transfer(String sourceName, String targetName, Double money);

}
