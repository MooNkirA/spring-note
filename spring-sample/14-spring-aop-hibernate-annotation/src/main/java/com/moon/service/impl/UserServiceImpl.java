package com.moon.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moon.dao.IUserDao;
import com.moon.entity.User;
import com.moon.service.IUserService;

/**
 * @类描述： 客户业务层实现类
 */
// 使用注解配置给spring容器管理
@Service("us")
public class UserServiceImpl implements IUserService {

    // 配置属性注入
    @Resource(name = "ud")
    private IUserDao userDao;

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
