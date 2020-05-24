package com.moon.spring.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

/**
 * Spring 框架中 InitializingBean 接口的示例
 * <p>
 * 如果需要在一个类实例化以后去做一些逻辑处理，那么就可以借助这个InitializingBean接口来完成。
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-5-18 23:31
 * @description
 */
public class InitMethodBean implements InitializingBean, BeanNameAware {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("****** 实现InitializingBean接口的afterPropertiesSet()方法执行了 ******");
    }

    /*
     * 在xml配置文件中<bean>标签中，配置init-method属性
     *  注意：通过xml配置文件的方式实现类创建后执行init-method，相应的方法的执行顺序是在实现了InitializingBean接口的afterPropertiesSet()方法执行之后
     */
    public void initMethod() {
        System.out.println("======= InitMethodBean.initMethod()方法执行了 =========");
    }

    /*
     * 实现BeanNameAware接口，实现 setBeanName 方法可以获取到当前实例的beanName
     */
    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware.setBeanName()方法获取当前实例化的beanName :: " + name);
    }
}
