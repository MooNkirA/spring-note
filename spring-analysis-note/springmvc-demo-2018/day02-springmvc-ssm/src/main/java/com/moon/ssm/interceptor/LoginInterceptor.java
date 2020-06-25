package com.moon.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户登陆拦截器
 * @author MoonZero
 */
public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * preHandle方法在请求方法前执行，对用户登陆验证
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/*
		 * 	1.访问商品列表数据，需要判断用户是否登录
		 * 	2.如果用户已经登录，直接让他访问商品列表
		 * 	3.如果用户未登录，先去登录页面进行登录，成功登录以后再访问商品列表
		 */
		
		// 1.获取session对象
		HttpSession session = request.getSession();
		
		// 2.从session域中获取用户数据
		if(session.getAttribute("user") == null) {
			// 2.1 用户没有登陆，跳转到登陆页面
			System.out.println("用户没有登陆..."); // 用于测试打印
			response.sendRedirect(request.getContextPath() + "/user/toLogin.do");
			return false;
		}
		
		// 2.2 用户已登陆登录，直接放行
		System.out.println("用户已经登陆。。。放行。。。"); // 用于测试打印
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
