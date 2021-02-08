package com.moon.spring.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 循环依赖示例素材
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-5 16:15
 * @description
 */
@Component
@Getter
@Setter
public class CircularRefC {

    // 在依赖注入时，如果属性是引用类型，会触发该类型的getBean操作
    @Autowired
    private CircularRefA circularRefA;
    @Autowired
    private CircularRefB circularRefB;

    public CircularRefC() {
        System.out.println("============CircularRefC()无参构造函数触发===========");
        System.out.println("CircularRefC自动注入属性circularRefA:" + circularRefA);
        System.out.println("CircularRefC自动注入属性circularRefB:" + circularRefB);
    }

}
