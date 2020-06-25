package com.moon.spring.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary // 有多个实现同一个接口，spring注入时会优先选择标识了 @Primary 注解实现类
@Service
public class UserServiceImpl1 implements UserService {

    @Override
    public String queryUser(String userId) {
        System.out.println("测试aop增强，UserServiceImpl1.queryUser()方法调用，入参userId->" + userId);
        return "UserServiceImpl1.queryUser()返回：" + userId;
    }

}
