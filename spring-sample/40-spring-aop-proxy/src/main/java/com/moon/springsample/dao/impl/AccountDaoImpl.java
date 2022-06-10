package com.moon.springsample.dao.impl;

import com.moon.springsample.dao.AccountDao;
import com.moon.springsample.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户持久层接口实现，为了不引入其他持久层框架 hibernate\mybatis\jpa等，所以自己实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-1 17:03
 * @description
 */
// 标识当前类由spring管理，创建并存入ioc容器中。与@Component注解作用一样，一般用于持久层，具有更加精确的语义化
@Repository
public class AccountDaoImpl implements AccountDao {

    // 自动按照类型注入容器中的对象
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void update(Account account) {
        jdbcTemplate.update("update account set name=?,money=? where id=?", account.getName(), account.getMoney(), account.getId());
    }

    @Override
    public Account findByName(String name) {
        // 根据用户名称查询sql
        List<Account> accountList = jdbcTemplate.query("select * from account where name = ?", new BeanPropertyRowMapper<>(Account.class), name);
        // 判断是否为空
        if (accountList.isEmpty()) {
            return null;
        }
        // 判断是否查询出多个结果
        if (accountList.size() > 1) {
            throw new IllegalArgumentException("账户名不唯一");
        }
        return accountList.get(0);
    }

}
