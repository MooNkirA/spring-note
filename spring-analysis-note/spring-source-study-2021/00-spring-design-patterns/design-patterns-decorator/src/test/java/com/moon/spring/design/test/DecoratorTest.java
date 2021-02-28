package com.moon.spring.design.test;

import com.moon.spring.design.decorator.OrdinaryPerson;
import com.moon.spring.design.decorator.Person;
import com.moon.spring.design.decorator.SuperSuperman;
import com.moon.spring.design.decorator.Superman;
import org.junit.Test;

/**
 * 装饰者设计模式测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-27 19:20
 * @description
 */
public class DecoratorTest {

    @Test
    public void testDecoratorBasic() {
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
