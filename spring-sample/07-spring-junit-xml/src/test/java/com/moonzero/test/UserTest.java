package com.moonzero.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.moonzero.entity.User;
import com.moonzero.service.IUserService;

/**
 * 测试业务层和持久层
 *
 * @author MoonZero
 */
// 注解替换原有运行器
@RunWith(SpringJUnit4ClassRunner.class)
// 指定 spring 配置文件的位置
@ContextConfiguration(locations = {"classpath:bean.xml"})
public class UserTest {

    @Autowired
    private IUserService us;

    /**
     * 测试查询方法
     */
    @Test
    public void testFindAll() {
        us.findAllUser();
    }

    /**
     * 测试新增用户
     */
    @Test
    public void testAddUser() {
        us.addUser(new User("测试你妹~~~", "123212131"));
    }
}
