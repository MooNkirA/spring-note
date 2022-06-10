package com.moon.springsample.service.impl;

import com.moon.springsample.dao.AccountDao;
import com.moon.springsample.domain.Account;
import com.moon.springsample.service.AccountService;
import com.moon.springsample.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 账户业务接口实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-1 16:58
 * @description
 */
// @Service
public class AccountServiceImpl implements AccountService {

    // 自动按照类型注入容器中的对象
    @Autowired
    private AccountDao accountDao;

    // 注入事务工具类
    @Autowired
    private TransactionManager transactionManager;

    /**
     * 转账，增加事务处理逻辑
     * 注：虽然事务控制生效，但这样会每个业务方法都需要重复以下的代码，所以需要使用aop去进行改造
     *
     * @param sourceName 转出账户名称
     * @param targetName 转入账户名称
     * @param money      转账金额
     */
    @Override
    public void transfer(String sourceName, String targetName, Double money) {
        try {
            // 开启事务
            transactionManager.begin();

            // 1.根据名称查询转出账户
            Account source = accountDao.findByName(sourceName);
            // 2.根据名称查询转入账户
            Account target = accountDao.findByName(targetName);
            // 3.转出账户减钱
            source.setMoney(source.getMoney() - money);
            // 4.转入账户加钱
            target.setMoney(target.getMoney() + money);
            // 5.更新转出账户
            accountDao.update(source);
            int i = 1 / 0; // 模拟转账异常
            // 6.更新转入账户
            accountDao.update(target);

            // 提交事务
            transactionManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 回滚事务
            transactionManager.rollback();
        } finally {
            // 关闭连接
            transactionManager.close();
        }

    }

}
