package com.moon.spring.test;

import com.moon.spring.config.AppConfig;
import com.moon.spring.service.LogService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 自定义 TargetSource 接口测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-3-6 16:23
 * @description
 */
public class CustomTargetSourceTest {

    private final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    /* TargetSource 接口测试 */
    @Test
    public void testTargetSourceBasic() {
        LogService logService = context.getBean(LogService.class);
        logService.logErrorMessage("You have an error!");
    }

}
