package com.moon.spring.service.impl;

import com.moon.spring.service.LogService;
import org.springframework.stereotype.Service;

/**
 * 日志业务类（测试素材）
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-25 22:54
 * @description
 */
@Service
public class LogServiceImpl implements LogService {

    @Override
    public String logErrorMessage(String message) {
        System.out.println("测试aop增强，LogServiceImpl.logErrorMessage()方法调用，入参message->" + message);
        return "LogServiceImpl.logErrorMessage()返回：" + message;
    }
}
