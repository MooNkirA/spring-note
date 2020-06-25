package com.moon.spring.spi;

/**
 * service provider interface
 * <P>服务提供接口，需要提供一个可配置的服务接口的实现类</P>
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-8 16:37
 * @description
 */
public interface SpiService {

    String query(String param);
    
}
