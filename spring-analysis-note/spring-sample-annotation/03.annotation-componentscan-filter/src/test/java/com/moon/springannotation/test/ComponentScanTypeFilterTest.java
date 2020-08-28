package com.moon.springannotation.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.service.OrderService;
import com.moon.springsample.service.PlatformService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ComponentScan 自定义过滤器S TypeFilter 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-27 23:53
 * @description
 */
public class ComponentScanTypeFilterTest {

    /**
     * 分别 直接包扫描不使用过滤器 与 使用自定义过滤后 测试
     */
    @Test
    public void customTypeFiltertest() {
        // 1. 传入项目配置类字节码方式，创建基于注解的spinrg容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2. 根据bean名称，从容器中获取订单与平台业务实现类，并调用方法
        OrderService orderService = context.getBean("orderService", OrderService.class);
        orderService.calcOrderDiscount("member");

        PlatformService platformService = context.getBean("platformService", PlatformService.class);
        platformService.calcSalePercentage("normal");
        /* 结果：如果不配置使用自定义过滤器扫描，区分加载不同场景的实现。此时会出现实现类名称相同的错误 */
    }

}
