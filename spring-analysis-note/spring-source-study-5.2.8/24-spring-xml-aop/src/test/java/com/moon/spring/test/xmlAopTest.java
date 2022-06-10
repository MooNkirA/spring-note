package com.moon.spring.test;

import com.moon.spring.service.LogService;
import com.moon.spring.util.ContextUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * 基于xml配置的aop测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-26 09:07
 * @description
 */
public class xmlAopTest {

    private final ApplicationContext context = ContextUtils.getXmlContext("spring.xml");

    /* 基于xml配置的aop测试 */
    @Test
    public void testAopBasic() {
        LogService logService = context.getBean(LogService.class);
        logService.logErrorMessage("You have an error!");
    }

}
