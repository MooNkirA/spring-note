package com.moonzero.service.impl;

import java.util.List;

import com.moonzero.dao.IUserDao;
import com.moonzero.entity.User;
import com.moonzero.service.IUserService;

/**
 * 用户业务逻辑层现实类
 *
 * @author MoonZero
 */
public class UserServiceImpl implements IUserService {

    private IUserDao dao;

    public void setDao(IUserDao dao) {
        this.dao = dao;
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public List<User> findAllUser() {
        return dao.findAllUser();
    }

}
