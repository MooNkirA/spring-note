package com.moon.spring.design.decorator;

/**
 * 对普通人类进去第一次增强
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-27 19:14
 * @description
 */
public class Superman extends Decorator {

    /* 定义构造函数，使用接口(或父类)去接收需要增强的类 */
    public Superman(Person person) {
        super(person);
    }

    /* 增强的方法 */
    public void learnLanguage() {
        OrdinaryPerson ordinaryPerson = (OrdinaryPerson) person;
        ordinaryPerson.getLanguageList().add("English");
        System.out.println("Superman学会的语言 --> " + ordinaryPerson.getLanguageList().toString());
    }

    @Override
    public void language() {
        // 调用原始类的方法
        super.language();
        // 调用增加部分
        learnLanguage();
    }

    /* 增强的方法 */
    public void learnMotion() {
        OrdinaryPerson ordinaryPerson = (OrdinaryPerson) person;
        ordinaryPerson.getMotionList().add("flying");
        System.out.println("Superman学会的运动 --> " + ordinaryPerson.getMotionList().toString());
    }

    @Override
    public void motion() {
        // 调用原始类的方法
        super.motion();
        // 调用增加部分
        learnMotion();
    }
}