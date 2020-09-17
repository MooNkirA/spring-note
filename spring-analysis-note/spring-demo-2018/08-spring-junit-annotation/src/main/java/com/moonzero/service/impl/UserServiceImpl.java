package com.moonzero.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonzero.dao.IUserDao;
import com.moonzero.entity.User;
import com.moonzero.service.IUserService;

/**
 * 用户业务逻辑层现实类
 *
 * @author MoonZero
 */
// 使用注解创建对象
@Service("userService")
public class UserServiceImpl implements IUserService {

    // 使用自动类型注入
    @Autowired
    private IUserDao dao;

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public List<User> findAllUser() {
        return dao.findAllUser();
    }

}
