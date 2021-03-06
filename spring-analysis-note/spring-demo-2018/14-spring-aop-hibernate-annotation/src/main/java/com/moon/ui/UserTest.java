package com.moon.ui;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.moon.config.SpringConfiguration;
import com.moon.entity.User;
import com.moon.service.IUserService;

/**
 * 用户测试
 * 
 * @author MoonZero
 */
public class UserTest {

	public static void main(String[] args) {
		// 获取spring容器操作对象
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		// 根据id获取对象
		IUserService us = (IUserService) context.getBean("us");

		// 根据id查询用户
		// System.out.println(us.findUserById(2));
		// System.out.println("=========================");
		// 查询用户
		List<User> users = us.findAllUser();
		for (User u : users) {
			System.out.println(u);
		}
		// 保存用户
		// User user = new User(null, "冰女", "仙");
		// us.saveUser(user);

		// 删除用户
		// User user = new User();
		// user.setId(19);
		// us.removeUser(user);

		// 更新用户
		// User user = new User(18, "改", "x");
		// us.updateUser(user);
	}
}
