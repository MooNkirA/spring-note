package com.moon.spring.designPattern.template;

/**
 * 模板设计模式 - 基础模板抽象类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2019-12-21 16:42
 * @description
 */
public abstract class AbstractBaseTemplate {

    /**
     * 主业务方法
     * 注：如果不希望子类去修改此主业务方法，可以使用final修饰该方法
     */
    protected  void mainBusiness() {
        method1();
        method2();
        method3();
    }

    /* 模板父类已经实现的业务方法 */
    public void method1() {
        System.out.println("====执行主业务方法1====");
    }

    public void method2() {
        System.out.println("====执行主业务方法2====");
    }

    /**
     * 该方法就是一个钩子方法，通过子类的实现干预父类的方法的业务流程
     * 钩子方法会通过子类实现后，挂载到父类方法中执行
     * 所以不同的子类，相应的实现业务逻辑不一样。
     */
    public abstract void method3();
}
