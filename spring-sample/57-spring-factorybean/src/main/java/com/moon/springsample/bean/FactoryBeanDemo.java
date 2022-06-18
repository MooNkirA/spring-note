package com.moon.springsample.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Spring 框架 FactoryBean 接口使用示例
 * FactoryBean 是泛型接口，如果不指定泛型，但 getObject() 方法返回值为 Object 类型
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-14 15:18
 * @description
 */
@Component("myFactoryBean")
public class FactoryBeanDemo implements FactoryBean<Bean1> {

    /*
     * 此方法可以进行一些其他的逻辑处理，然后返回一个新的bean。该 bean 是单例还是多例取决于 isSingleton 方法的返回值
     *  注：此处返回的新的实例与原来实现了FactoryBean接口当前类的实例互不干扰，都会被spring管理
     */
    @Override
    public Bean1 getObject() throws Exception {
        Bean1 bean = new Bean1();
        System.out.println("getObject() create bean: " + bean);
        return bean;
    }

    /*
     * 指定 getObjectType() 方法返回对象的类型，如果不确定该返回对象的类型，则返回 null
     *  值得注意的是，若返回 null，在使用 getBean 方法根据类型获取此实例时，会报错！
     */
    @Override
    public Class<?> getObjectType() {
        return Bean1.class;
    }

    /*
     * 用于指定 getObject() 方法返回对象是否为单例
     * 若返回单例，则返回 true，若返回多例，则返回 false。方法的默认实现返回 true
     */
    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
