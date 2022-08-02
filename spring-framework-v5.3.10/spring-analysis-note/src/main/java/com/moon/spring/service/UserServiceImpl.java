package com.moon.spring.service;

import org.springframework.stereotype.Service;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-07-31 17:33
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

	@Override
	public void foo() {
		System.out.println("do something...");
	}

}
