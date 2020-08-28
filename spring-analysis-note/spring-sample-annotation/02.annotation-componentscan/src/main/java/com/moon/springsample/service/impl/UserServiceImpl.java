package com.moon.springsample.service.impl;

import com.moon.springsample.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户业务层实现类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-2 12:20
 * @description
 */
@Service("userService") /* 配置当前类交给spring ioc容器管理，其中value为对象在容器中的名称 */
public class UserServiceImpl implements UserService {

    @Override
    public void saveUser() {
        System.out.println("成功保存用户");
    }

}
