package com.moon.springsample.factory;

import com.moon.springsample.service.AccountService;
import com.moon.springsample.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * 用于生产service代理对象的工厂
 * 需求：此简单的示例，只实现对AccountService的代理创建，同时加入事务
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-5 23:39
 * @description
 */
@Component
public class ServiceProxyFactory {

    /* 注入被代理对象 */
    @Autowired
    private AccountService accountService;

    /* 注入事务管理工具类 */
    @Autowired
    private TransactionManager transactionManager;

    /**
     * 获取账户业务层代理
     *
     * @return
     */
    // 注册到spring容器中
    @Bean("accountServiceProxy")
    public AccountService getAccountServiceProxy() {
        // 1. 创建动态代理
        Object accountServiceProxy = Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), (proxy, method, args) -> {
            // 定义返回值
            Object result = null;

            try {
                // 开启事务
                transactionManager.begin();

                // 调用被代理对象的方法
                result = method.invoke(accountService, args);

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
            // 返回
            return result;
        });
        // 返回代理对象
        return (AccountService) accountServiceProxy;
    }

}
