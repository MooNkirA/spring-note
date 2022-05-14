package com.moon.springsample.dao.impl;

import com.moon.springsample.dao.AccountDao;
import org.springframework.stereotype.Repository;

/**
 * 模拟账户持久层实现2
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 14:02
 * @description
 */
@Repository
public class AccountDaoImplTwo implements AccountDao {

    @Override
    public void save() {
        System.out.println("AccountDaoImplTwo.save()方法执行，保存账户数据");
    }

}
