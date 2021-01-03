package com.moon.spring.spi;

/**
 * service provider interface
 * <P>服务提供接口，需要提供一个可配置的服务接口的实现类</P>
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-2 19:17
 * @description
 */
public interface SpiService {

    String query(String param);

}
