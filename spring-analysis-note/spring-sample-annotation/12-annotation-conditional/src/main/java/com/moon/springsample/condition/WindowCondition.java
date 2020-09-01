package com.moon.springsample.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * 自定义bean注册条件 - window系统时注册
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-1 08:45
 * @description
 */
public class WindowCondition implements Condition {

    /**
     * 定义是否注册到ioc容器的条件实现逻辑
     *
     * @param context  IOC容器的上下文对象
     * @param metadata
     * @return 当返回true时，代表在@Conditional注解标识此自定义注册条件实现类的bean对象可以注册到容器中。否则不注册
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        /* 此ConditionContext上下文对象可以获取以下spring框架对象 */
        // 获取ioc使用的beanFactory对象
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        // 获取bean定义信息(BeanDefinition)的注册中心
        BeanDefinitionRegistry registry = context.getRegistry();

        /* ******** 本地示例实际逻辑部分 ********  */
        // 1. 获取当前操作环境（用于判断是window还是linux系统）
        Environment environment = context.getEnvironment();
        // 输出一下环境信息，查看一下里面的具体的内容
        if (environment instanceof StandardEnvironment) {
            // 判断是否标准的环境对象（StandardEnvironment），此类可以查看相应的信息
            StandardEnvironment standardEvn = (StandardEnvironment) environment;
            // 获取环境信息map集合
            Map<String, Object> systemProperties = standardEvn.getSystemProperties();
            // 循环输出
            for (Map.Entry<String, Object> entry : systemProperties.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        // 2. 获取当前系统的名称
        String osName = environment.getProperty("os.name");
        // osName = "os.Linux"; // 这里用于模拟linux系统环境
        // 3. 根据名称判断当前系统类型，包含Windows则说明是windows系统
        if (osName.contains("Windows")) {
            // 返回true，代表将bean注册到容器中
            return true;
        }
        // 返回false，代表不注册到容器中
        return false;
    }
}
