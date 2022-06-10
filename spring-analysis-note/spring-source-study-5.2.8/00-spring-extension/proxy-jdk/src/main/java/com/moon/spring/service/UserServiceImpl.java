package com.moon.spring.service;

/**
 * 被代理类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-19 20:48
 * @description
 */
public class UserServiceImpl implements UserService {

    @Override
    public String addUserId(String id) {
        System.out.println("新增用户ID: " + id);
        return id;
    }

}
