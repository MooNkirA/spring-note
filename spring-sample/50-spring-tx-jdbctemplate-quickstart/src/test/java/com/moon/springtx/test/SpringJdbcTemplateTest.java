package com.moon.springtx.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * JdbcTemplate 基础使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-13 11:34
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class SpringJdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /* 测试是否已成功注入 */
    @Test
    public void testJdbcTemplate() {
        System.out.println(jdbcTemplate);
    }

    /* 新增测试 */
    @Test
    public void testSave() {
        jdbcTemplate.update("insert into account(name,money)values(?,?)", "敌法师", 6712d);
    }

    /* 更新测试 */
    @Test
    public void testUpdate() {
        jdbcTemplate.update("update account set name=?,money=? where id=?", "敌法师", 23456d, 6);
    }

    /* 删除测试 */
    @Test
    public void testDelete() {
        jdbcTemplate.update("delete from account where id = ?", 6);
    }

    /* 单个数据查找测试 */
    @Test
    public void testFindOne() {
        // 方式一：查询出列表后，再取第一个数据
        BeanPropertyRowMapper<Account> beanPropertyRowMapper = new BeanPropertyRowMapper<>(Account.class);
        List<Account> accountList = jdbcTemplate.query("select * from account where id = ?", beanPropertyRowMapper, 6);
        System.out.println(accountList.isEmpty() ? null : accountList.get(0));

        // 方式二：查询单个对象
        Account account = jdbcTemplate.queryForObject("select * from account where id = ?", new BeanPropertyRowMapper<>(Account.class), 6);
        System.out.println(account);
    }

    /* 根据条件查询数据集合测试 */
    @Test
    public void testQueryForList() {
        /*
         * 查询集合，指定行记录的封装类型
         *   <T> List<T> query(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
         *       第2个参数rowMapper：是指定sql查询后转换的对象类型
         */
        List<Account> accountList = jdbcTemplate.query("select * from account where money > ?", new BeanPropertyRowMapper<>(Account.class), 1400d);
        for (Account account : accountList) {
            System.out.println(account);
        }
        System.out.println("-------------------------------------------");

        /*
         * 查询指定某种类型（单个字段）的集合
         *  <T> List<T> queryForList(String sql, Class<T> elementType, @Nullable Object... args)
         *      第2个参数elementType：是指定sql查询后的数据类型
         */
        List<Double> moneyList = jdbcTemplate.queryForList("select money from account where money > ?", Double.class, 1400d);
        for (Double m : moneyList) {
            System.out.println(m);
        }
        System.out.println("-------------------------------------------");

        /*
         * 查询返回Map类型的集合，不指定类型的时候，返回是一个Map数据结构的集合
         *  List<Map<String, Object>> queryForList(String sql, @Nullable Object... args)
         */
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from account where money > ?", 1400d);
        for (Map<String, Object> map : mapList) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
        }
    }

    /* 统计查询测试 */
    @Test
    public void testFindCount() {
        Long total = jdbcTemplate.queryForObject("select count(*) from account where money > ?", Long.class, 1400d);
        System.out.println("查询总记录数：" + total);
    }

    /* 查询所有数据测试 */
    @Test
    public void testFindAll() {
        List<Account> accountList = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<>(Account.class));
        for (Account account : accountList) {
            System.out.println(account);
        }
    }

    /* 查询数据封装成Map结构集合测试 */
    @Test
    public void testQueryForMap() {
        Map<String, Object> map = jdbcTemplate.queryForMap("select * from account where id = ?", 5d);
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    /* 查询SqlRowSet测试 */
    @Test
    public void testQueryForRowSet() {
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from account where money > ?", 1400d);
        System.out.println(sqlRowSet);

        // 处理SqlRowSet对象，next()方法判断是否有下一个元素
        while (sqlRowSet.next()) {
            String name = sqlRowSet.getString("name");
            System.out.println(name);
        }
    }

}
