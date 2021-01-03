package com.moon.spring.spi;

import java.util.ServiceLoader;

/**
 * SPI 服务接口测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-2 19:32
 * @description
 */
public class SpiTest {

    /**
     * 此设计的好处是：实现业务代码解耦，扩展性高。核心的业务不需要再修改，日后增加新的业务需求时，可以通过增加新的实现类与修改配置文件即可
     * 缺点是：粒度不够细，通过配置的方式不能唯一确定一个实现类
     */
    public static void main(String[] args) {
        // 通过jdk的api，ServiceLoader获取配置文件中定义所有实现类实例
        ServiceLoader<SpiService> load = ServiceLoader.load(SpiService.class);

        // 调用实现类的业务方法
        for (SpiService spiService : load) {
            spiService.query("呵呵");
        }
    }

}
