package com.moon.service.impl;

import java.util.List;

import com.moon.dao.IUserDao;
import com.moon.entity.User;
import com.moon.service.IUserService;

/**
 * @类描述： 客户业务层实现类
 *
 */
public class UserServiceImpl implements IUserService {

	private IUserDao userDao;

	// 属性注入，要提供set方法
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	// 保存用户
	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	// 根据id查询用户
	@Override
	public User findUserById(Integer id) {
		return userDao.findUserById(id);
	}

	// 查询所有用户
	@Override
	public List<User> findAllUser() {
		return userDao.findAllUser();
	}

	// 删除用户
	@Override
	public void removeUser(User User) {
		userDao.removeUser(User);
	}

	// 更新用户
	@Override
	public void updateUser(User User) {
		userDao.updateUser(User);
	}
}
