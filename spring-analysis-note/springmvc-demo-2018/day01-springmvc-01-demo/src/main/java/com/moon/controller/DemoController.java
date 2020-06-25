package com.moon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试SpringMVC中的controller
 * @author MoonZero
 */
// 使用注解让spring容器管理
@Controller
public class DemoController {
	
	/*
	 * @RequestMapping：配置请求url，
	 * 	当请求的url为/hello.do，执行当前方法
	 * 
	 * ModelAndView：模型和视图。
	 * 	用于设置响应的模型数据;
	 * 	用于设置响应的视图
	 */
	@RequestMapping("/hello.do")
	public ModelAndView hello() {
		
		// 1.创建ModelAndView对象
		ModelAndView mav = new ModelAndView();
		
		/*
		 * 2.设置响应的模型与视图 
		 * addObject方法：设置响应的模型数据
		 *	attributeName参数：模型的名称
		 * 	attributeValue参数：模型数据
		 */
		mav.addObject("hello", "springMVC第1次测试使用");
		/*
		 * 3.设置响应的视图
		 * setViewName方法：设置响应的视图的名称
		 * 	viewName参数：视图的名称(页面的物理路径)
		 */
		// mav.setViewName("/WEB-INF/jsp/helloSpringMVC.jsp");
		// 配置视图解析器后只需要设置视图名称即。（原因是视图解析器进行拼接）
		mav.setViewName("helloSpringMVC");
		
		// 4.返回模型视图对象
		return mav;
	}
}
