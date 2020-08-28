package com.moon.springsample.service.impl;

import com.moon.springsample.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-5 23:31
 * @description
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Override
    public void deleteAccount() {
        System.out.println("成功删除账号");
    }
    
}
