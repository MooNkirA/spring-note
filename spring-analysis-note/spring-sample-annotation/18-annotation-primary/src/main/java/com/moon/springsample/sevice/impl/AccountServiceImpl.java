package com.moon.springsample.sevice.impl;

import com.moon.springsample.dao.AccountDao;
import com.moon.springsample.sevice.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 模拟账号业务接口实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 14:08
 * @description
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    /*
     * 使用@Autowired按类型自动注入。因为AccountDao的两个实现存入ioc容器，此时会报错。
     *  在不使用@Qualifier注解指定按名称注入的情况下，可以在相应多个实现类上，将想优先注入的类上标识@Primary注解，提高注入的优先级
     */
    @Autowired
    private AccountDao accountDao;

    @Override
    public void save() {
        accountDao.save();
    }

}
