package com.moon.spring.designPattern.template;

/**
 * 基础模板子类B，实现自己的业务逻辑
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-12-21 16:53
 * @description
 */
public class TemplateClassB extends AbstractBaseTemplate {

    // 实现B类自己的业务逻辑
    @Override
    public void method3() {
        System.out.println("我是模板子类B，我执行B的业务逻辑。。。。。");
    }

}
