package com.moon.spring.factorybean;

import com.moon.spring.bean.Cat;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Spring 框架 FactoryBean 接口使用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-12 19:07
 * @description
 */
// FactoryBean是泛型接口，如果不指定泛型，但getObject()方法返回值为Object类型
@Component
public class FactoryBeanDemo implements FactoryBean<Cat> {

    /*
     *  此方法可以进行一些其他的逻辑处理，然后返回一个新的bean
     *   注：此处返回的新的实例与原来实现了FactoryBean接口当前类的实例互不干扰，都会被spring管理
     */
    @Override
    public Cat getObject() throws Exception {
        System.out.println("FactoryBeanDemo.getObject()方法执行了....");
        Cat cat = new Cat();
        cat.setName("I am born form FactoryBeanDemo");
        cat.setColor("purple");
        return cat;
    }

    @Override
    public Class<?> getObjectType() {
        return Cat.class;
    }
}
