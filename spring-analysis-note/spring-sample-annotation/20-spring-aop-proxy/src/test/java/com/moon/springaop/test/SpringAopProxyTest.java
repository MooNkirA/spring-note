package com.moon.springaop.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * AOP 基础使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-5 15:30
 * @description
 */
// 设置使用spring框架的测试容器，注解替换原有运行器
@RunWith(SpringJUnit4ClassRunner.class)
// 指定 spring 配置文件的位置(配置类),参数值为数组，如果只有一个值{}省略
@ContextConfiguration(classes = SpringConfiguration.class)
public class SpringAopProxyTest {

    // @Autowired注解是按类型去自动注入，所以这里注入还是原来的实现类实例(即AccountServiceImpl_Proxy)
    @Autowired
    @Qualifier("accountServiceProxy") // 使用@Qualifier指定代理在容器中的名称，就可以注入代理对象
    private AccountService accountService;

    /* 代理模式测试，经典转账案例 */
    @Test
    public void transferTest() {
        accountService.transfer("Moon", "石原里美", 100D);
    }

}
