package com.moon.spring.beanlifecycle.test;

import com.moon.spring.beanlifecycle.config.WebConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 基础测试类，用于多个测试类继承，减少编写注解
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-10-2 22:58
 * @description
 */
@SpringBootTest(classes = {WebConfig.class})
@RunWith(SpringRunner.class)
public class BaseTest {
}
