package com.moon.spring.service;

import com.moon.spring.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private Student student;

    /*
     * 测试spring.xml文件，bean标签的autowire-candidate属性
     *  当autowire-candidate属性设置为false时，则无法自己注入，启动时会报错
     *  No qualifying bean of type 'com.moon.spring.service.AccountService' available
     */
    // @Autowired
    // private AccountService accountService;

    @Override
    public String queryUser(String userId) {
        return "xxx";
    }

}
