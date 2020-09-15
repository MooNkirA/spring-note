package com.moon.springtx.test;

import com.moon.springsample.JdbcTemplate;
import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.domain.Account;
import com.moon.springsample.handler.ResultSetHandler;
import com.moon.springsample.handler.impl.BeanHandler;
import com.moon.springsample.handler.impl.BeanListHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSet;
import java.util.List;

/**
 * 自定义 JdbcTemplate 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-15 15:15
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class CustomJdbcTemplateTest {

    /* 注入自定义JdbcTemplate（非spring框架） */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /* 测试是否已成功注入 */
    @Test
    public void testJdbcTemplate() {
        System.out.println(jdbcTemplate); // 输出结果：com.moon.springsample.JdbcTemplate@1fa268de
    }

    /* 新增测试 */
    @Test
    public void testSave() {
        jdbcTemplate.update("insert into account(name,money)values(?,?)", "樱木花道", 6712d);
    }

    /* 更新测试 */
    @Test
    public void testUpdate() {
        jdbcTemplate.update("update account set name=?,money=? where id=?", "樱木花道", 321d, 9);
    }

    /* 删除测试 */
    @Test
    public void testDelete() {
        jdbcTemplate.update("delete from account where id = ?", 8);
    }

    /* 单个数据查找测试 */
    @Test
    public void testFindOne() {
        // 创建自定义的结果处理器
        ResultSetHandler<Account> handler = new BeanHandler<>(Account.class);
        Account account = (Account) jdbcTemplate.query("select * from account where id = ?", handler, 9);
        System.out.println(account);

    }

    /* 查询所有数据测试 */
    @Test
    public void testFindAll() {
        List<Account> accountList = (List<Account>) jdbcTemplate.query("select * from account", new BeanListHandler<>(Account.class));
        for (Account account : accountList) {
            System.out.println(account);
        }
    }

}
