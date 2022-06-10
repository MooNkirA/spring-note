package com.moon.springsample.service.impl;

import com.moon.springsample.dao.AccountDao;
import com.moon.springsample.domain.Account;
import com.moon.springsample.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户业务接口实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-1 16:58
 * @description
 */
// 标识当前类由spring管理，创建并存入ioc容器中。与@Component注解作用一样，一般用于业务层，具有更加精确的语义化
@Service
public class AccountServiceImpl implements AccountService {

    // 自动按照类型注入容器中的对象
    @Autowired
    private AccountDao accountDao;

    @Override
    public void save(Account account) {
        accountDao.save(account);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    public void delete(Integer id) {
        accountDao.delete(id);
    }

    @Override
    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

}
