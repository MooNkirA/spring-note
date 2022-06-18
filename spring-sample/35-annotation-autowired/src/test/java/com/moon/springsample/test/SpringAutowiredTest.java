package com.moon.springsample.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 注解 @Autowired 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 08:58
 * @description
 */
// 设置使用spring框架的测试容器，注解替换原有运行器
@RunWith(SpringJUnit4ClassRunner.class)
// 指定 spring 配置文件的位置(配置类),参数值为数组，如果只有一个值{}省略
@ContextConfiguration(classes = SpringConfiguration.class)
public class SpringAutowiredTest {

    /*
     * 使用@Autowired注解自动按类型注入对象实例。此注解有required属性，默认值true，表示必须注入成功
     *  如果容器中没有此类型的实例，则会报“找不到此类型实例”
     *  如果容器中有多个同类型的实例，则会报“预期是单个，但找到2个可匹配的实例”
     *  如果设置属性required = false，则不会报错，但该对象无法注入，为null
     *  如果容器中有多个同类型的实例并且bean的id不一样，如果标识@Autowired注解的变量名称与其中一个bean的id一致则会注入成功，否则报错
     */
    // @Autowired(required = false)
    @Autowired(required = true)
    @Qualifier("userServiceOther") // @Qualifier注解与@Autowired注解配合使用，一般用于容器中存在多个同类型的bean实例，@Qualifier注解来指定注入的bean的ID
    private UserService userService;

    @Test
    public void autowiredBasicTest() {
        System.out.println(userService);
        // 调用自动按类型注入的对象
        userService.saveUser();
    }

}
