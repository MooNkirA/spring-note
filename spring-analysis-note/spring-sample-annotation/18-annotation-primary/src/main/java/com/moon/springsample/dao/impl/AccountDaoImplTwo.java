package com.moon.springsample.dao.impl;

import com.moon.springsample.dao.AccountDao;
import org.springframework.context.annotation.Primary;
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
// 用于指定bean的注入优先级。被@Primary修饰的bean对象优先注入
@Primary
public class AccountDaoImplTwo implements AccountDao {

    @Override
    public void save() {
        System.out.println("AccountDaoImplTwo.save()方法执行，保存账户数据");
    }

}
