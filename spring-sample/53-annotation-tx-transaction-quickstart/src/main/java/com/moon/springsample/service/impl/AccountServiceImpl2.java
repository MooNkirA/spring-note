package com.moon.springsample.service.impl;

import com.moon.springsample.dao.AccountDao;
import com.moon.springsample.domain.Account;
import com.moon.springsample.event.MyApplicationEvent;
import com.moon.springsample.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 账户的业务层接口实现 - 使用编程式事务的模板对象TransactionTemplate实现事务控制。
 * 此时，不需要使用@Transactional注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-15 22:43
 * @description
 */
@Service("accountService")
public class AccountServiceImpl2 implements AccountService {

    // 注入dao接口
    @Autowired
    private AccountDao accountDao;

    /* 注入Spring的事件发布对象 */
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /* 注入编程式事务的模板对象TransactionTemplate */
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void transfer(String sourceName, String targetName, Double money) {
        transactionTemplate.execute(status -> {
            /* 业务的核心代码都是写在此TransactionCallback<T>函数式接口的doInTransaction方法内部的 */
            try {
                // 1. 根据名称查询转出账户
                Account source = accountDao.findByName(sourceName);
                // 2. 根据名称查询转入账户
                Account target = accountDao.findByName(targetName);
                // 3. 转出账户减钱
                source.setMoney(source.getMoney() - money);
                // 4. 转入账户加钱
                target.setMoney(target.getMoney() + money);
                // 5. 更新转出账户
                accountDao.update(source);
                int i = 1 / 0; // 模拟转账异常
                // 6. 更新转入账户
                accountDao.update(target);
            } finally {
                // 发布事件逻辑（定义在finally中，让无论转账完成或者失败回滚都会执行）
                Map<String, Object> map = new HashMap<>();
                map.put("sourceName", sourceName);
                map.put("targetName", targetName);
                map.put("money", money);
                applicationEventPublisher.publishEvent(new MyApplicationEvent(map));
            }
            return null;
        });
    }

}
