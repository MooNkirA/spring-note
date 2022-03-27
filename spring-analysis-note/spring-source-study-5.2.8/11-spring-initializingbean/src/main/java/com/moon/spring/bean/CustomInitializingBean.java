package com.moon.spring.bean;

import org.springframework.beans.factory.InitializingBean;

/**
 * Spring 框架中 InitializingBean 接口的示例
 * <p>如果需要在一个类实例化以后去做一些逻辑处理，那么就可以借助这个InitializingBean接口来完成。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-3 11:10
 * @description
 */
public class CustomInitializingBean implements InitializingBean {

    public CustomInitializingBean() {
        System.out.println("CustomInitializingBean无参构造方法执行了....");
    }

    /*
     * 实现此接口的 afterPropertiesSet 方法，是在当前bean实例化后被调用的
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("CustomInitializingBean类实现InitializingBean接口的afterPropertiesSet()方法执行了....");
    }

    /*
     * 在xml配置文件中<bean>标签中，配置init-method属性
     *  注意：通过xml配置文件的方式实现类创建后执行init-method，相应的方法的执行顺序是在实现了InitializingBean接口的afterPropertiesSet()方法执行之后
     */
    public void initMehtod() {
        System.out.println("xml配置的CustomInitializingBean.initMethod()方法执行了....");
    }

}
