package com.moon.spring.test;

import com.moon.spring.bean.ScopedProxyBean;
import com.moon.spring.util.ContextUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * 作用域代理测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-3-7 22:24
 * @description
 */
public class ScopedProxyTest {

    private final ApplicationContext context = ContextUtils.getAnnotationContext();

    @Test
    public void testScopedProxy() {
        ScopedProxyBean scopedProxyBean = context.getBean("scopedProxyBean", ScopedProxyBean.class);

        for (int i = 0; i < 5; i++) {
            scopedProxyBean.testDefaultProxyModeBean();
        }
        System.out.println("=================");
        for (int i = 0; i < 5; i++) {
            scopedProxyBean.testTargetClassProxyModeBean();
        }
    }

}
