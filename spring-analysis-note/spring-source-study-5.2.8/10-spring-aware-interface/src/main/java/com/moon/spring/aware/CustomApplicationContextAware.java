package com.moon.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextAware 接口运用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-3 14:47
 * @description
 */
@Component
public class CustomApplicationContextAware implements ApplicationContextAware {

    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method. Invoked after {@link ResourceLoaderAware#setResourceLoader},
     * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
     * {@link MessageSourceAware}, if applicable.
     * <p>
     * 实现ApplicationContextAware接口，通过 setApplicationContext 方法可以获取此类所运行在的 ApplicationContext 容器
     * 通常，此调用将用于初始化对象。
     * 执行时机：在填充正常的bean属性之后，初始化回调（例如 `InitializingBean＃afterPropertiesSet()` 或自定义的初始化方法(init-method)之前调用。
     * 在如 `ResourceLoaderAware#setResourceLoader`、`ApplicationEventPublisherAware#setApplicationEventPublisher`、`MessageSourceAware`等之后调用
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     *                           该对象当前所存在的ApplicationContext容器对象
     * @throws ApplicationContextException in case of context initialization errors
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 此方法可以获取当前运行的spring容器对象
        System.out.println("CustomApplicationContextAware.setApplicationContext()方法执行了....");
        System.out.println("调用容器的applicationContext.getId() :: " + applicationContext.getId());
    }

}
