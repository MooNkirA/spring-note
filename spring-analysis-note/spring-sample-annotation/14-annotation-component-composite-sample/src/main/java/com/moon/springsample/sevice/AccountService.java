package com.moon.springsample.sevice;

import com.moon.springsample.domain.Account;

import java.util.List;

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
     * 保存账户
     *
     * @param account
     */
    void save(Account account);

    /**
     * 更新账户
     *
     * @param account
     */
    void update(Account account);

    /**
     * 删除账户
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据id查询账户
     *
     * @param id
     * @return
     */
    Account findById(Integer id);

    /**
     * 查询所有账户
     *
     * @return
     */
    List<Account> findAll();
}
