package com.moon.springsample.dao.impl;

import com.moon.springsample.dao.AccountDao;
import com.moon.springsample.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户的持久层接口实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-15 22:48
 * @description
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    // 注入JdbcTemplate操作对象
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void update(Account account) {
        jdbcTemplate.update("update account set name=?,money=? where id = ?", account.getName(), account.getMoney(), account.getId());
    }

    @Override
    public Account findByName(String name) {
        List<Account> accounts = jdbcTemplate.query("select * from account where name = ?", new BeanPropertyRowMapper<>(Account.class), name);
        if (accounts.isEmpty()) {
            return null;
        }
        if (accounts.size() > 1) {
            throw new RuntimeException("账户不唯一");
        }
        return accounts.get(0);
    }

}
