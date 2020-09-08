package com.moon.springsample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Spring项目配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-6 17:51
 * @description
 */
@Configuration
@ComponentScan("com.moon.springsample")
// 开启spring注解aop配置的支持，如不加上此注解，所有切面类逻辑都无法生效
// @EnableAspectJAutoProxy
/*
 * proxyTargetClass属性，指定代理的方式
 *   默认是false，使用jdk的代理；基于接口生成代理类
 *   如果设置为true，则使用cglib方式是基于子类生成代理，此时业务的实现类不能用final修饰（因为final修饰的类不能被继承）
 */
// @EnableAspectJAutoProxy(proxyTargetClass = true)
// 指定是否暴露代理对象，默认值是false。如果暴露则通过AopContext可以进行访问
@EnableAspectJAutoProxy(exposeProxy = true)
public class SpringConfiguration {
}
