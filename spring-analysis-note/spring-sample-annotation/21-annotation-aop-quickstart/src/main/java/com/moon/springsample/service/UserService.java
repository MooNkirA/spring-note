package com.moon.springsample.service;

import com.moon.springsample.domain.User;

/**
 * 用户业务层接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-6 17:39
 * @description
 */
public interface UserService {

    /**
     * 模拟保存用户
     *
     * @param user
     */
    void saveUser(User user);

}
