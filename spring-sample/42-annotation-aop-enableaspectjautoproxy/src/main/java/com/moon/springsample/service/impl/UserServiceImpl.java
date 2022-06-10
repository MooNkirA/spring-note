package com.moon.springsample.service.impl;

import com.moon.springsample.domain.User;
import com.moon.springsample.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务接口实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-6 17:40
 * @description
 */
@Service("userService")
// 如果设置@EnableAspectJAutoProxy(proxyTargetClass = true)，则不能使用final修改此实现类，因为不能生成子类
public final class UserServiceImpl implements UserService {

    @Override
    public void saveUser(User user) {
        System.out.println("UserServiceImpl.saveUser()执行了保存用户" + user.toString());
    }

    @Override
    public void saveAllUser(List<User> users) {
        // 设置@EnableAspectJAutoProxy(exposeProxy = true)，可以通过AopContext获取，暴露aop的代理对象
        UserService proxy = (UserService) AopContext.currentProxy();
        for (User user : users) {
            // 因为定义切入点没有包含saveAllUser方法，所以不被切面增强，就算调用切入点的方法，也不会有增强的效果
            // this.saveUser(user);
            // 此时通过代理对象去调用切入点的方法即可
            proxy.saveUser(user);
        }
    }

}
