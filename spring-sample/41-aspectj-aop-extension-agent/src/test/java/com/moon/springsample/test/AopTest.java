package com.moon.springsample.test;

import com.moon.springsample.service.AgentClassLoaderDemo;
import org.junit.Test;

/**
 * 基于 agent 类加载实现的 AOP 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-21 22:50
 * @description
 */
public class AopTest {

    /*
        注意几点
        1. 版本选择了 java 8, 因为目前的 aspectj-maven-plugin 1.14.0 最高只支持到 java 16
        2. 运行时需要在 VM options 里加入 -javaagent:D:/development/maven/repository/org/aspectj/aspectjweaver/1.9.7/aspectjweaver-1.9.7.jar
           注：上面示例本人直接使用 maven 仓库的 jar 包
    */
    @Test
    public void tsetAgentClassLoaderAop() {
        // 不依赖 Spring，通过修改编译的文件实现
        AgentClassLoaderDemo agentClassLoaderDemo = new AgentClassLoaderDemo();
        // 3.执行普通方法，观察是否有增强
        agentClassLoaderDemo.normalMethod();
        // 4.执行静态方法，观察是否有增强
        AgentClassLoaderDemo.staticMethod();
    }

}
