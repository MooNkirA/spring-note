package com.moon.spring.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * 多例，作用域代理属性为ScopedProxyMode.TARGET_CLASS，创建基于子类的作用域代理
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-3-7 22:19
 * @description
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TargetClassProxyModeBean {

    public void getHashCode() {
        System.out.println(this.hashCode());
    }

}
