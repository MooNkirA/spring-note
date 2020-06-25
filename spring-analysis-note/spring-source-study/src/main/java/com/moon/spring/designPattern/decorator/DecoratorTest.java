package com.moon.spring.designPattern.decorator;

/**
 * 装饰者设计模式测试
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-5 12:06
 * @description
 */
public class DecoratorTest {

    public static void main(String[] args) {
        Person ordinaryPerson = new OrdinaryPerson();
        System.out.println("=======普通人=========");
        ordinaryPerson.language();
        ordinaryPerson.motion();
        System.out.println("=======普通人=========\n");

        System.out.println("=======超人增强=========");
        Person superman = new Superman(ordinaryPerson);
        superman.language();
        superman.motion();
        System.out.println("=======超人增强=========\n");

        System.out.println("=======超超人两次增强=========");
        Person ordinaryPerson1 = new OrdinaryPerson();
        Person superSuperman = new SuperSuperman(ordinaryPerson1);
        superSuperman.language();
        superSuperman.motion();
        System.out.println("=======超超人两次增强=========");
    }

}
