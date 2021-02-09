package com.moon.spring.test;

import com.moon.spring.bean.DestroyDemoBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * DisposableBean 接口测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-8 16:11
 * @description
 */
public class DisposableBeanTest {

    private final ApplicationContext context =
            new AnnotationConfigApplicationContext("com.moon.spring");

    @Test
    public void testDisposableBeanBasic() {
        DestroyDemoBean bean = context.getBean("destroyDemoBean", DestroyDemoBean.class);
        System.out.println(bean);

        /*
         * AutowireCapableBeanFactory 对象的 destroyBean 方法，需要传入的Bean对象，
         * 如果此bean类实现DisposableBean接口，则调用destroy方法
         * 否则只做销毁bean的操作
         */
        // context.getAutowireCapableBeanFactory().destroyBean(bean);

        // 调用上下文对象关闭方法，会调用到容器中所有 DisposableBean 类型的 destroy 方法
        ((AnnotationConfigApplicationContext) context).close();
    }

}
