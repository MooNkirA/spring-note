package com.moonzero.service;

import java.util.List;

import com.moonzero.entity.User;

public interface IUserService {

    /**
     * @return
     */
    List<User> findAllUser();

    /**
     * 保存用户
     *
     * @param user
     */
    void addUser(User user);
}
