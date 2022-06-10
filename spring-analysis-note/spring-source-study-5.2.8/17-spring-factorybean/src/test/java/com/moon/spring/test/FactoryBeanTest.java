package com.moon.spring.test;

import com.moon.spring.bean.Cat;
import com.moon.spring.factorybean.FactoryBeanDemo;
import com.moon.spring.util.ContextUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * FactoryBean 接口测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-12 19:23
 * @description
 */
public class FactoryBeanTest {

    private final ApplicationContext context = ContextUtils.getAnnotationContext();

    @Test
    public void testFactoryBeanBasic() {
        // 实现了FactoryBean接口的类，通过bean的id只能获取该类实现了getObject()方法返回的对象实例
        Cat cat = (Cat) context.getBean("factoryBeanDemo");
        System.out.println(cat); // Cat(name=I am born form FactoryBeanDemo, color=purple)

        // 如果要获取实现了FactoryBean接口的类的实例，只能通过【"&" + beanName】来获取实例
        FactoryBeanDemo factoryBean = context.getBean("&factoryBeanDemo", FactoryBeanDemo.class);
        System.out.println(factoryBean); // com.moon.spring.factorybean.FactoryBeanDemo@5c909414
    }

}
