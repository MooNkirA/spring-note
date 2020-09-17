package com.moon.dao.impl;

import java.util.List;

import com.moon.dao.IUserDao;
import com.moon.entity.User;
import com.moon.utils.HibernateUtil;

/**
 * @类描述： 用户持久层实现类
 *
 */
public class UserDaoImpl implements IUserDao {

	/**
	 * 保存用户
	 */
	@Override
	public void saveUser(User user) {
		HibernateUtil.getSession().save(user);
	}

	/**
	 * 查找全部用户
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUser() {
		return HibernateUtil.getSession().createQuery("from User").list();
	}

	/**
	 * 删除用户
	 */
	@Override
	public void removeUser(User user) {
		HibernateUtil.getSession().delete(user);
	}

	/**
	 * 查询一个用户
	 */
	@Override
	public User findUserById(Integer id) {
		return HibernateUtil.getSession().get(User.class, id);
	}

	/**
	 * 更新用户
	 */
	@Override
	public void updateUser(User user) {
		HibernateUtil.getSession().update(user);
	}

}
