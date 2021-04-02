package com.moon.spring.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * 多例，作用域代理属性为缺省值，不创建作用域代理
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-3-7 22:15
 * @description
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.DEFAULT)
public class DefaultProxyModeBean {

    public void getHashCode() {
        System.out.println(this.hashCode());
    }

}
