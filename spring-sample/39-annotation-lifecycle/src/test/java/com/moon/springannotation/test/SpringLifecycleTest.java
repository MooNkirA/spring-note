package com.moon.springannotation.test;

import com.moon.springsample.bean.CustomBean;
import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.processor.MyBeanPostProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Spring 容器对象的生命周期测试
 * 涉及 @PostConstruct 与 @PreDestroy 、 @Scope
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 22:01
 * @description
 */
public class SpringLifecycleTest {

    @Test
    public void lifecycleBasicTest() {
        // 创建容器，观察单例对象的初始化方法
        System.out.println("************* 容器准备创建 *************");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        System.out.println("************* 容器创建完毕 *************");

        /*
            1. 测试基于 @Bean 注解的 initMethod 与 destroyMethod 方法实现生命周期
                - 移除 SpringConfiguration 类中的 @Bean 注释后进行测试
            2. 测试基于 InitializingBean & DisposableBean 接口实现生命周期
                - 移除 UserService 类中的 @Component 注释后进行测试
            3. 测试基于 @PostConstruct & @PreDestroy 注解方式实现生命周期
                - 移除 LogUtil 类中的 @Component 注释后进行测试
            4. 测试基于 BeanPostProcessor 后置处理器接口实现生命周期
                - 移除 MyBeanPostProcessor 类中的 @Component 注释后进行测试

            注：以上注释最好只放开一组进行测试，否则日志输出太多不容易观察
         */
        // System.out.println(context.getBean(CustomBean.class)); // 用于测试多例

        System.out.println("************* 容器准备关闭 *************");
        // 关闭容器，观察单例对象的销毁前的方法
        context.close();
        System.out.println("************* 容器关闭完毕 *************");
    }

}
