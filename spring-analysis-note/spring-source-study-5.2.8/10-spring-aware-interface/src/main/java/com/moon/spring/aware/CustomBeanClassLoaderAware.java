package com.moon.spring.aware;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * BeanClassLoaderAware 接口运用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-3 15:55
 * @description
 */
@Component
public class CustomBeanClassLoaderAware implements BeanClassLoaderAware {

    /**
     * Callback that supplies the bean {@link ClassLoader class loader} to
     * a bean instance.
     * <p>Invoked <i>after</i> the population of normal bean properties but
     * <i>before</i> an initialization callback such as
     * {@link InitializingBean InitializingBean's}
     * {@link InitializingBean#afterPropertiesSet()}
     * method or a custom init-method.
     * <p>
     * 实现 BeanClassLoaderAware 接口，通过 setBeanClassLoader 方法可以获取类加载器
     * 将 ClassLoader 提供给bean实例化后回调
     * 执行时机：在填充正常的bean属性之后，在初始化回调（例如 `InitializingBean#afterPropertiesSet()` 的方法或自定义 init-method 之前调用。
     *
     * @param classLoader the owning class loader
     *                    类加载器
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("CustomBeanClassLoaderAware.setBeanClassLoader()方法执行了....");
        System.out.println("ClassLoader#getParent() :: " + classLoader.getParent());
    }

}
