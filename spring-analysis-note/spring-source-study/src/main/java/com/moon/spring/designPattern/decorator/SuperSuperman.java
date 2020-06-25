package com.moon.spring.designPattern.decorator;

/**
 * 对Superman进行第二次增强
 * <p>
 * 装饰者模式与代理模式的区别
 * <p>装饰者模式：注重点在于对现有的类中方法的进行持续增强，而且可以多次相互装饰。</p>
 * <p>代理模式： 注重点在于对现有类的功能进行修改和拦截，甚至代替</p>
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-5 13:16
 * @description
 */
public class SuperSuperman extends Superman {

    /* 定义构造函数，使用接口(或父类)去接收需要增强的类 */
    public SuperSuperman(Person person) {
        super(person);
    }

    /* 第二次增强的方法 */
    public void learnNewLanguage() {
        // 对象superman对象进去二次增强
        OrdinaryPerson ordinaryPerson = (OrdinaryPerson) person;
        // 在Superman基础上再装饰，增加外星语言
        ordinaryPerson.getLanguageList().add("alien language");
        System.out.println("SuperSuperman学会的语言 --> " + ordinaryPerson.getLanguageList().toString());
    }

    @Override
    public void language() {
        // 调用第一次增加的方法
        super.language();
        // 调用增加部分
        learnNewLanguage();
    }

    /* 第二次增强的方法 */
    public void learnNewMotion() {
        OrdinaryPerson ordinaryPerson = (OrdinaryPerson) person;
        // 在Superman基础上再装饰，增加额外的运动能力
        ordinaryPerson.getMotionList().add("天锁斩月");
        System.out.println("SuperSuperman学会的运动 --> " + ordinaryPerson.getMotionList().toString());
    }

    @Override
    public void motion() {
        // 调用第一次增加的方法
        super.motion();
        // 调用增加部分
        learnNewMotion();
    }
}
