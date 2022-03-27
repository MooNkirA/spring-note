package com.moon.spring.aware;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * BeanNameAware 接口运用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-3 14:00
 * @description
 */
@Component
public class CustomBeanNameAware implements BeanNameAware {

    /**
     * Set the name of the bean in the bean factory that created this bean.
     * <p>Invoked after population of normal bean properties but before an
     * init callback such as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method.
     * <p>
     * 实现BeanNameAware接口，setBeanName 方法可以获取到当前实例的beanName。
     * 执行时机：在填充正常的bean属性之后，在如 `InitializingBean＃afterPropertiesSet()` 之类的初始化回调或自定义的初始化方法之前调用。
     *
     * @param name the name of the bean in the factory.
     *             Note that this name is the actual bean name used in the factory, which may
     *             differ from the originally specified name: in particular for inner bean
     *             names, the actual bean name might have been made unique through appending
     *             "#..." suffixes. Use the {@link BeanFactoryUtils#originalBeanName(String)}
     *             method to extract the original bean name (without suffix), if desired.
     *             当前实例注册到BeanFactory中的bean的名称
     *             请注意，该名称是工厂中使用的实际bean名称，可能与最初指定的名称不同：
     *             特别是对于内部类的bean名称，通过添加“＃...”后缀可以使实际bean名称具有唯一性。
     *             如果需要，可以使用 `BeanFactoryUtils＃originalBeanName(String)` 方法提取原始bean名称（不带后缀）。
     */
    @Override
    public void setBeanName(String name) {
        System.out.println("CustomBeanNameAware.setBeanName()方法调用，方法入参[name]:: " + name);
    }
}
