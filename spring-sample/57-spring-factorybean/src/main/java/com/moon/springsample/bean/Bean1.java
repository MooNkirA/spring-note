package com.moon.springsample.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试 bean
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-14 15:18
 * @description
 */
public class Bean1 implements BeanFactoryAware, InitializingBean {

    private Bean2 bean2;

    // 使用 FactoryBean 方式创建实例，不会触发依赖注入
    @Autowired
    public void setBean2(Bean2 bean2) {
        System.out.println("setBean2() 方法注入 bean2: " + bean2);
        this.bean2 = bean2;
    }

    public Bean2 getBean2() {
        return bean2;
    }

    // 使用 FactoryBean 方式创建实例，不会触发 Aware 系列接口回调
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Bean1 的 setBeanFactory 方法执行了...");
    }

    // 使用 FactoryBean 方式创建实例，不会触发 InitializingBean 接口的 afterPropertiesSet
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean1 的 afterPropertiesSet 方法执行了...");
    }
}
