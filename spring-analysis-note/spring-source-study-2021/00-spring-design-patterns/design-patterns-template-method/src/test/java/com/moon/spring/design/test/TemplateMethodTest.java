package com.moon.spring.design.test;

import com.moon.spring.design.template.AbstractBaseTemplate;
import com.moon.spring.design.template.TemplateClassB;
import org.junit.Test;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-27 19:22
 * @description
 */
public class TemplateMethodTest {

    @Test
    public void testTemplateMethodBasic(){
        // 执行A类的业务逻辑
        // AbstractBaseTemplate baseTemplate = new TemplateClassA();
        // 执行B类的业务逻辑
        AbstractBaseTemplate baseTemplate = new TemplateClassB();

        // 设计基础模板父类的主业务流程方法
        baseTemplate.mainBusiness();
    }

}
