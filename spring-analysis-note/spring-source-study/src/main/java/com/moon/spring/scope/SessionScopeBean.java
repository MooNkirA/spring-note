package com.moon.spring.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

/**
 * 用于测试spring bean的 session 作用范围
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-6-3 23:35
 * @description
 */
@Component
@Scope(value = RequestAttributes.REFERENCE_SESSION/*,proxyMode = ScopedProxyMode.TARGET_CLASS*/)
public class SessionScopeBean {
}
