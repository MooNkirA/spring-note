package com.moon.springsample.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 自定义bean注册条件 - linux系统时注册
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-1 09:04
 * @description
 */
public class LinuxCondition implements Condition {

    /**
     * 定义是否注册到ioc容器的条件实现逻辑
     *
     * @param context
     * @param metadata
     * @return 当返回true时，代表在@Conditional注解标识此自定义注册条件实现类的bean对象可以注册到容器中。否则不注册
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 1. 获取当前操作环境（用于判断是window还是linux系统）
        Environment environment = context.getEnvironment();
        // 2. 获取当前系统的名称
        String osName = environment.getProperty("os.name");
        // osName = "os.Linux"; // 这里用于模拟linux系统环境
        // 3. 根据名称判断当前系统类型，包含"Linux"则说明是Linux系统
        if (osName.contains("Linux")) {
            // 返回true，代表将bean注册到容器中
            return true;
        }
        // 返回false，代表不注册到容器中
        return false;
    }

}
