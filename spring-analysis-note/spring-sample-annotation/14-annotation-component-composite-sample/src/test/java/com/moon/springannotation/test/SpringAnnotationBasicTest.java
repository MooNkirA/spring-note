package com.moon.springannotation.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.domain.Account;
import com.moon.springsample.sevice.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * spring 注解综合案例测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-1 22:40
 * @description
 */
// 设置使用spring框架的测试容器，注解替换原有运行器
@RunWith(SpringJUnit4ClassRunner.class)
// 指定 spring 配置文件的位置(配置类),参数值为数组，如果只有一个值{}省略
@ContextConfiguration(classes = SpringConfiguration.class)
public class SpringAnnotationBasicTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("spring annotation basic test");
        account.setMoney(1234d);
        accountService.save(account);
    }

    @Test
    public void testUpdate() {
        Account account = accountService.findById(2);
        account.setName("update?!");
        account.setMoney(2222d);
        accountService.update(account);
    }

    @Test
    public void testDelete() {
        accountService.delete(3);
    }

    @Test
    public void testFindById() {
        Account account = accountService.findById(1);
        System.out.println(account);
    }

    @Test
    public void testFindAll() {
        List<Account> accounts = accountService.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }


}
