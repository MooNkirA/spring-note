package com.moon.springsample.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.processor.AtBeanPostProcessor;
import com.moon.springsample.processor.ComponentScanPostProcessor;
import com.moon.springsample.processor.MapperScanPostProcessor;
import org.junit.Test;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 自定义 BeanFactoryPostProcessor 后置处理器测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-17 13:56
 * @description
 */
public class BeanFactoryPostProcessorTest {

    /*
     * 自定义 BeanFactoryPostProcessor 实现 @ComponentScan、@Bean、解析 Mapper 接口等功能
     */
    @Test
    public void testCustomBeanFactoryPostProcessor() {
        // 创建无其他扩展功能的容器 GenericApplicationContext
        GenericApplicationContext context = new GenericApplicationContext();
        // 注册配置类
        context.registerBean("springConfiguration", SpringConfiguration.class);

        // 注册模拟 @ComponentScan 功能的自定义 BeanFactoryPostProcessor 实现
        context.registerBean(ComponentScanPostProcessor.class);
        // 注册模拟 @Bean 功能的自定义 BeanFactoryPostProcessor 实现
        context.registerBean(AtBeanPostProcessor.class);
        // 注册模拟扫描 mybatis mapper 接口生成代理实例功能的自定义 BeanFactoryPostProcessor 实现
        context.registerBean(MapperScanPostProcessor.class);

        // 初始化容器
        context.refresh();

        // 输出目前容器管理的所有实例
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        // 销毁容器
        context.close();
    }

}
