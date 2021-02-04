package com.moon.spring.aware;

import com.moon.spring.component.ComponentA;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

/**
 * BeanFactoryAware 运用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-3 14:21
 * @description
 */
@Component
public class CustomBeanFactoryAware implements BeanFactoryAware {

    private ComponentA componentA;

    /**
     * Callback that supplies the owning factory to a bean instance.
     * <p>Invoked after the population of normal bean properties
     * but before an initialization callback such as
     * {@link InitializingBean#afterPropertiesSet()} or a custom init-method.
     * <p>
     * 实现BeanFactoryAware接口，在该类实例后执行 setBeanFactory 回调方法。
     * 执行时机是在填充常规bean属性之后但在初始化回调（例如自定义init-method）之前调用。
     *
     * @param beanFactory owning BeanFactory (never {@code null}).
     *                    The bean can immediately call methods on the factory.
     *                    Bean实例工厂(不会为null)，Bean可以立即在工厂调用方法
     * @throws BeansException in case of initialization errors
     * @see BeanInitializationException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("CustomBeanFactoryAware.setBeanFactory()方法执行了....");
        // 可以通过BeanFactory对象获取一些对象的信息与数据
        componentA = beanFactory.getBean("componentA", ComponentA.class);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CustomBeanFactoryAware.class.getSimpleName() + "[", "]")
                .add("componentA=" + componentA)
                .toString();
    }

}
