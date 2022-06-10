package com.moon.spring.aware;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * EnvironmentAware 接口的运用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-3 15:21
 * @description
 */
@Component
public class CustomEnvironmentAware implements EnvironmentAware {

    /**
     * Set the {@code Environment} that this component runs in.
     * <p>
     * 运行程序的环境
     *
     * @param environment 程序运行环境对象
     */
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("CustomEnvironmentAware.setEnvironment()方法执行了....");
        System.out.println("Environment对象读取properties文件的moon.name属性 :: " + environment.getProperty("moon.name"));
    }

}
