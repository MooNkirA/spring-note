package com.moon.springsample.dao;

import com.moon.springsample.domain.Account;

/**
 * 账户的持久层接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-15 22:47
 * @description
 */
public interface AccountDao {

    /**
     * 更新账户
     *
     * @param account
     */
    void update(Account account);

    /**
     * 根据名称查询账户
     *
     * @param name
     * @return
     */
    Account findByName(String name);

}
