package com.moonzero.service.impl;

import com.moonzero.dao.IUserDao;
import com.moonzero.service.IUserService;
import com.moonzero.utils.BeanFactory;

public class UserServiceImpl implements IUserService {

    // 获取数据访问层对象
    private IUserDao dao = (IUserDao) BeanFactory.getBean("userDao");

    @Override
    public void add(String name) {
        // 调用数据访问层方法
        dao.add(name);
    }

}
