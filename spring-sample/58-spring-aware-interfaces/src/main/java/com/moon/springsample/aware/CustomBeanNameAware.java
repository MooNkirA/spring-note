package com.moon.springsample.aware;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-18 9:59
 * @description
 */
@Component
public class CustomBeanNameAware implements BeanNameAware, InitializingBean {

    /**
     * Set the name of the bean in the bean factory that created this bean.
     * <p>Invoked after population of normal bean properties but before an
     * init callback such as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method.
     *
     * @param name the name of the bean in the factory.
     *             Note that this name is the actual bean name used in the factory, which may
     *             differ from the originally specified name: in particular for inner bean
     *             names, the actual bean name might have been made unique through appending
     *             "#..." suffixes. Use the {@link BeanFactoryUtils#originalBeanName(String)}
     *             method to extract the original bean name (without suffix), if desired.
     */
    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware 接口用于获取当前 bean 在容器的名称：" + name);
    }

    // 测试与 BeanNameAware 接口的 setBeanName 方法调用顺序
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("CustomBeanNameAware 实现 InitializingBean 接口的 afterPropertiesSet 方法执行...");
    }

    // 测试与 BeanNameAware 接口的 setBeanName 方法调用顺序
    @PostConstruct
    public void init() {
        System.out.println("CustomBeanNameAware 类 @PostConstruct 修饰的方法执行...");
    }

}
