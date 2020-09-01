package com.moon.springannotation.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.moon.springsample.config.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 注解 @Profile 测试，由于需要指定不同环境，此示例使用junit的 @ActiveProfiles 注解来模拟实际web项目指定环境的设置
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-1 15:15
 * @description
 */
// 设置使用spring框架的测试容器
@RunWith(SpringJUnit4ClassRunner.class)
// 指定配置类
@ContextConfiguration(classes = SpringConfiguration.class)
// 用于指定当前测试示例使用的环境
@ActiveProfiles("test")
public class SpringProfileTest {

    @Autowired
    private DruidDataSource dataSource;

    @Test
    public void profileBasicTest() {
        // 测试从ioc容器中DI依赖注入的数据源对象的最大连接数，判断是否与当前环境一致
        System.out.println(dataSource.getMaxActive());
    }

}
