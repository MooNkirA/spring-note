package com.moon.spring.design.decorator;

/**
 * 定义装饰者类，是人类对象进行装饰，所以需要实现人类接口
 * <p>
 * 装饰者规定了装饰流程，就是规定了接口调用过程，具体子类实例方法如何调用子类去实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-27 19:14
 * @description
 */
public abstract class Decorator implements Person {

    public Person person;

    public Decorator(Person person) {
        this.person = person;
    }

    @Override
    public void language() {
        person.language();
    }

    @Override
    public void motion() {
        person.motion();
    }
}