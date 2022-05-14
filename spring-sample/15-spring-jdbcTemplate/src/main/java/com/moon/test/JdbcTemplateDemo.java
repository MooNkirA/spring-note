package com.moon.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.moon.entity.Account;

public class JdbcTemplateDemo {
	public static void main(String[] args) {
		// 创建spring容器操作对象
		ApplicationContext context = new ClassPathXmlApplicationContext("beanSpring.xml");
		// 根据id获取对象
		JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");

		// 操作数据库插入操作
		// String sql1 = "insert into account(name,money) values ('测试一下','122.3')";
		// jt.execute(sql1);

		// 查询所有数据操作
		String sql2 = "select * from account;";
		List<Account> accounts = jt.query(sql2, new AccountRowMapper());

		// 遍历集合
		for (Account a : accounts) {
			System.out.println(a);
		}

		// 查询一个数据
		String sql3 = "select * from account where id=?;";
		List<Account> accounts2 = jt.query(sql3, new AccountRowMapper(), 6);
		// 根据id查询，只有一个值
		System.out.println(accounts2.isEmpty() ? "没有查询到数据" : accounts2.get(0));

		// 查询返回一行一列操作
		String sql4 = "select COUNT(*) from account where money<?";
		int count = jt.queryForObject(sql4, Integer.class, 150);
		System.out.println(count);

	}
}

class AccountRowMapper implements RowMapper<Account> {

	/**
	 * 结果集有几条数据，就执行几次此方法
	 * 		ResultSet：是查询一行的返回的结果集
	 * 		rowNum：这一行结果集，所在集合中的下标(索引)
	 */
	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("结果集有几条数据，就执行几次此方法,rowNum = " + rowNum);
		// 根据列名获取值
		// Account account = new Account(rs.getInt("id"), rs.getString("name"), rs.getFloat("money"));
		// 根据列索引获取值
		Account account = new Account(rs.getInt(1), rs.getString(2), rs.getFloat(3));
		return account;
	}

}
