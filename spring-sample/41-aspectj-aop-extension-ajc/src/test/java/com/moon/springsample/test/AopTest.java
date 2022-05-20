package com.moon.springsample.test;

import com.moon.springsample.service.AjcCompilerDemo;
import org.junit.Test;

/**
 * 基于 ajc 编译器实现 AOP 扩展测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-20 19:58
 * @description
 */
public class AopTest {

    /* 基于 ajc 编译器实现 AOP 测试 */
    @Test
    public void testAjcCompilerAop() {
        // 不依赖 Spring，通过修改编译的文件实现
        AjcCompilerDemo ajcCompilerDemo = new AjcCompilerDemo();
        // 3.执行普通方法，观察是否有增强
        ajcCompilerDemo.normalMethod();
        // 4.执行静态方法，观察是否有增强
        AjcCompilerDemo.staticMethod();
    }

}
