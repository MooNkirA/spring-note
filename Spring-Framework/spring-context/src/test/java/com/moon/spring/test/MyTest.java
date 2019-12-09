package com.moon.spring.test;

import com.moon.spring.bean.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试源码工程是否成功导入
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-12-9 18:19
 * @description
 */
public class MyTest {

	@Test
	public void test() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		Student student = (Student) applicationContext.getBean("student");
		System.out.println(student.getUserName());
	}

}
