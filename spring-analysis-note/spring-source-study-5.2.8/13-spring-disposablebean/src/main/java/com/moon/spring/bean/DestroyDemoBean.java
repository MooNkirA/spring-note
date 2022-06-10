package com.moon.spring.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * 实现 DisposableBean 接口，测试Bean的销毁
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-8 16:09
 * @description
 */
@Component
public class DestroyDemoBean implements DisposableBean {

    /**
     * Invoked by the containing {@code BeanFactory} on destruction of a bean.
     * <p>
     * 由包含在bean销毁中的容器调用。
     *
     * @throws Exception in case of shutdown errors. Exceptions will get logged
     *                   but not rethrown to allow other beans to release their resources as well.
     */
    // 在Bean实例销毁时，会执行到destroy()方法的逻辑
    @Override
    public void destroy() throws Exception {
        System.out.println("+++++DisposableBean接口的实现，DestroyDemoBean实例的destroy()方法执行+++++");
    }

}
