package com.moonzero.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.moonzero.dao.IUserDao;
import com.moonzero.entity.User;

/**
 * 用户持久层实现类
 *
 * @author MoonZero
 */
// 使用注解创建对象
@Repository("userDao")
public class UserDaoImpl implements IUserDao {

    @Override
    public void addUser(User user) {
        System.out.println(user.getName() + "====执行了用户持久层实现类保存方法！");
    }

    @Override
    public List<User> findAllUser() {
        System.out.println("查询到所有用户。");
        return null;
    }
}
