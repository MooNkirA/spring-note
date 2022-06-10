package com.moon.springsample.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * 测试 prototype 作用域失效问题
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-19 11:12
 * @description
 */
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class PrototypeScopeBean2 {
}
