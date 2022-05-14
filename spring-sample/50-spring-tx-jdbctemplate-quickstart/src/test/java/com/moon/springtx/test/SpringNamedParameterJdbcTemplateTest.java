package com.moon.springtx.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NamedParameterJdbcTemplate 基础使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-14 22:49
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class SpringNamedParameterJdbcTemplateTest {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /* 测试查询 */
    @Test
    public void testFind() {
        // 定义sql具名名称的值，key需要与具名名称相对应，与顺序无关
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        // 具名名称，SQL 按名称(以冒号开头)而不是按位置进行指定，这样不会受到以前“?”占位符位置的限制
        Account account = jdbcTemplate.queryForObject("select * from account where id = :id", map, new BeanPropertyRowMapper<>(Account.class));
        System.out.println(account);
    }

    /* 测试根据id查询多个数据 */
    @Test
    public void testFindByIds() {
        // 定义sql具名名称的值，key需要与具名名称相对应，与顺序无关
        Map<String, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(7);
        map.put("ids", list);
        // 具名名称，SQL 按名称(以冒号开头)而不是按位置进行指定，这样不会受到以前“?”占位符位置的限制
        List<Account> account = jdbcTemplate.query("select * from account where id in (:ids)", map, new BeanPropertyRowMapper<>(Account.class));
        System.out.println(account);
    }

    /* 测试新增 */
    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("新垣结衣");
        account.setMoney(2222d);
        // bean转成map
        BeanMap beanMap = BeanMap.create(account);
        jdbcTemplate.update("insert into account(name, money) values(:name, :money)", beanMap);
    }

}
