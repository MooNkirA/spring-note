package com.moon.spring.spi;

/**
 * SPI服务接口实现类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-8 16:40
 * @description
 */
public class SpiServiceImpl implements SpiService {

    @Override
    public String query(String param) {
        System.out.println("=======SpiServiceImpl.query()方法执行了======");
        return "OK";
    }

}
