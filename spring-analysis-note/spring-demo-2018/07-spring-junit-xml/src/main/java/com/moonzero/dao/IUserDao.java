package com.moonzero.dao;

import java.util.List;

import com.moonzero.entity.User;

/**
 * 用户持久层接口
 *
 * @author MoonZero
 */
public interface IUserDao {
    /**
     * 模拟增加用户方法
     */
    void addUser(User user);

    /**
     * 查询所有用户
     *
     * @return List<User>
     */
    List<User> findAllUser();
}
