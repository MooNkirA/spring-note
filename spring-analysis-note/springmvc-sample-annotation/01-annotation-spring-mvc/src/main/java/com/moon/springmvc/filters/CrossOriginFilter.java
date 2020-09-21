package com.moon.springmvc.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义过滤器，用于解决跨域请求的问题
 * <p>注：自定义过滤器需要实现Filter接口，并且在web配置类中的onStartup方法将其注册到web容器，才能生效
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-21 09:32
 * @description
 */
public class CrossOriginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 过滤处理的逻辑
     *
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            // 1. 强制带http协议的请求与响应对象
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            System.out.println("解决跨域的过滤器CrossOriginFilter执行了...");

            // 2. 设置response的响应消息头实现跨域问题的解决
            /* 允许跨域的主机地址 */
            response.setHeader("Access-Control-Allow-Origin", "*");
            /* 允许跨域的请求方法GET, POST, HEAD 等 */
            response.setHeader("Access-Control-Allow-Methods", "*");
            /* 重新预检验跨域的缓存时间 (s) */
            response.setHeader("Access-Control-Max-Age", "3600");
            /* 允许跨域的请求头 */
            response.setHeader("Access-Control-Allow-Headers", "*");
            /* 是否携带cookie */
            response.setHeader("Access-Control-Allow-Credentials", "true");

            // 3. 放行
            chain.doFilter(request, response);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
