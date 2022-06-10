package com.moon.springsample.test;

import com.moon.springsample.config.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring Aware 系统接口测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-18 9:51
 * @description
 */
public class AwareInterfacesTest {

    @Test
    public void basicTest() {
        // 创建容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        // 关闭容器
        context.close();
    }

}
