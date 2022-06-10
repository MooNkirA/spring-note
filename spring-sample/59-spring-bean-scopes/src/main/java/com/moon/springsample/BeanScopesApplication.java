package com.moon.springsample;

import com.moon.springsample.bean.ApplicationScopeBean;
import com.moon.springsample.bean.PrototypeScopeBean;
import com.moon.springsample.bean.RequestScopeBean;
import com.moon.springsample.bean.SessionScopeBean;
import com.moon.springsample.bean.SingletonScopeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Spring Bean 作用范围测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-19 11:06
 * @description
 */
@SpringBootApplication
@RestController
public class BeanScopesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanScopesApplication.class, args);
    }

    @Autowired
    private SingletonScopeBean singletonScopeBean;

    @Lazy
    @Autowired
    private PrototypeScopeBean prototypeScopeBean;

    @Lazy
    @Autowired
    private RequestScopeBean requestScopeBean;

    @Lazy
    @Autowired
    private SessionScopeBean sessionScopeBean;

    @Lazy
    @Autowired
    private ApplicationScopeBean applicationScopeBean;

    @GetMapping(value = "/test", produces = "text/html")
    public String test(HttpServletRequest request, HttpSession session) {
        ServletContext sc = request.getServletContext();
        return "<ul>" +
                "<li>" + "singleton scope:" + singletonScopeBean + "</li>" +
                "<li>" + "prototype scope:" + prototypeScopeBean + "</li>" +
                "<li>" + "request scope:" + requestScopeBean + "</li>" +
                "<li>" + "session scope:" + sessionScopeBean + "</li>" +
                "<li>" + "application scope:" + applicationScopeBean + "</li>" +
                "</ul>";
    }

}
