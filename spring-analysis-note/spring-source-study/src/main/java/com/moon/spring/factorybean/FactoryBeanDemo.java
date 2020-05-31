package com.moon.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Spring 框架 FactoryBean 接口使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-5-31 12:51
 * @description
 */
@Component
public class FactoryBeanDemo implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        /*
         *  此处可以进行一些其他的逻辑处理，然后返回一个新的bean
         *   注：此处返回的新的实例与原来实现了FactoryBean接口的此类的实例互不干扰
         */
        return new FactoryBeanOther();
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryBeanOther.class;
    }

}
