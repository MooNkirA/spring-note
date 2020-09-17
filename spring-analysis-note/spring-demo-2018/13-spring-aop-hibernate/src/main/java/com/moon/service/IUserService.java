package com.moon.service;

import java.util.List;

import com.moon.entity.User;

/**
 * @类描述： 客户业务层接口
 *
 */
public interface IUserService {
	
	/**
	 * 保存客户
	 * @param user
	 */
	void saveUser(User user);
	
	/**
	 * 查询所有客户
	 * @return
	 */
	List<User> findAllUser();
	
	/**
	 * 删除客户
	 * @param user
	 */
	void removeUser(User user);
	
	/**
	 * 根据id查询客户
	 * @param custId
	 * @return
	 */
	User findUserById(Integer id);
	
	/**
	 * 修改客户
	 * @param user
	 */
	void updateUser(User user);
	
}
