package com.moon.spring.spi;

/**
 * SPI 服务接口实现类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-2 19:21
 * @description
 */
public class SpiServiceImpl implements SpiService {

    @Override
    public String query(String param) {
        System.out.println(String.format("=======SpiServiceImpl.query(%s)方法执行了======", param));
        return "OK";
    }

}
