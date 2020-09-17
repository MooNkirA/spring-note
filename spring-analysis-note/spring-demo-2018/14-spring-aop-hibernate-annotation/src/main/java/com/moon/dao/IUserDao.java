package com.moon.dao;

import java.util.List;

import com.moon.entity.User;

/**
 * @类描述： 持久层接口
 */
public interface IUserDao {

    /**
     * 保存用户
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findAllUser();

    /**
     * 删除客户
     *
     * @param user
     */
    void removeUser(User user);

    /**
     * 根据id查询客户
     *
     * @param id
     * @return
     */
    User findUserById(Integer id);

    /**
     * 更新客户
     *
     * @param user
     */
    void updateUser(User user);

}
