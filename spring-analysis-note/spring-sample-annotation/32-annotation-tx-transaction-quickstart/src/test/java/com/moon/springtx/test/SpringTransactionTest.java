package com.moon.springtx.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基于纯注解声明式事务测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-15 23:13
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class SpringTransactionTest {

    /* 注入业务层接口 */
    @Autowired
    private AccountService accountService;

    /* 测试转账 */
    @Test
    public void testTransfer() {
        accountService.transfer("石原里美", "新垣结衣", 100d);
    }

}
