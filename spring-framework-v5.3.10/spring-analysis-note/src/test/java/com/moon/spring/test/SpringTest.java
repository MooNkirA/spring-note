package com.moon.spring.test;

import com.moon.spring.config.SpringConfig;
import com.moon.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring 基础测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-31 17:39
 * @description
 */
public class SpringTest {

	// 基础测试
	@Test
	public void test01() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		UserService userService = context.getBean(UserService.class);
		userService.foo();
	}

}
