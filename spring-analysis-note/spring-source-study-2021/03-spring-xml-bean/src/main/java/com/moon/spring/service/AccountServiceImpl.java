package com.moon.spring.service;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-3 16:43
 * @description
 */
public class AccountServiceImpl implements AccountService {

    @Override
    public String queryAccount(String id) {
        return "query account id is : " + id;
    }

}
