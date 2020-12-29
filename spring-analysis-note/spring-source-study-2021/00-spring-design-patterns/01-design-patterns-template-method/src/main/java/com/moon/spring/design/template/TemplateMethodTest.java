package com.moon.spring.design.template;

/**
 * 模板设计模式测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-12-27 13:23
 * @description
 */
public class TemplateMethodTest {

    public static void main(String[] args) {
        // 执行A类的业务逻辑
        // AbstractBaseTemplate baseTemplate = new TemplateClassA();
        // 执行B类的业务逻辑
        AbstractBaseTemplate baseTemplate = new TemplateClassB();

        // 设计基础模板父类的主业务流程方法
        baseTemplate.mainBusiness();
    }

}
