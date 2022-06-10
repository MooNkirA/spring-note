package com.moon.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义异常处理器
 * @author MoonZero
 */
public class SsmExceptionResolver implements HandlerExceptionResolver {

	// 方法内实现处理异常的业务逻辑
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		// 1.检查系统异常
		SsmException ssmException = null;
		if (ex instanceof SsmException) {
			// 如果是自定义异常子类，可以进行强转
			ssmException = (SsmException) ex;
		} else {
			// 不是父类指定子类
			ssmException = new SsmException("未知异常！");
		}

		// 2.创建模型视图对象
		ModelAndView mav = new ModelAndView();
		// 设置模型响应数据
		mav.addObject("message", ssmException.getMsg());
		// 设置模型视图
		mav.setViewName("error/error");
		// 返回模型视图对象
		return mav;
	}

}
