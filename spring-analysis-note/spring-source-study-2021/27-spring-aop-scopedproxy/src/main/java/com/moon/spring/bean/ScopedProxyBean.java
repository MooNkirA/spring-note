package com.moon.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用于测试@Autowired注入多例实例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-3-7 22:10
 * @description
 */
@Component
public class ScopedProxyBean {

    @Autowired
    private DefaultProxyModeBean defaultProxyModeBean;
    @Autowired
    private TargetClassProxyModeBean targetClassProxyModeBean;

    public void testDefaultProxyModeBean() {
        defaultProxyModeBean.getHashCode();
    }

    public void testTargetClassProxyModeBean() {
        targetClassProxyModeBean.getHashCode();
    }

}
