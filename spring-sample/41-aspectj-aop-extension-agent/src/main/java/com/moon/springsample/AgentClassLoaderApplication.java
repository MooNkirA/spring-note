package com.moon.springsample;

import com.moon.springsample.service.AgentClassLoaderDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-22 13:10
 * @description
 */
@SpringBootApplication
public class AgentClassLoaderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AgentClassLoaderApplication.class, args);
        // 不依赖 Spring，通过修改编译的文件实现
        // AgentClassLoaderDemo agentClassLoaderDemo = new AgentClassLoaderDemo();
        AgentClassLoaderDemo agentClassLoaderDemo = context.getBean(AgentClassLoaderDemo.class);
        System.out.println("service class: " + agentClassLoaderDemo.getClass());
        // 3.执行普通方法，观察是否有增强
        agentClassLoaderDemo.normalMethod();
        // 4.执行静态方法，观察是否有增强
        AgentClassLoaderDemo.staticMethod();
    }
}
