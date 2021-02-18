package com.moon.spring.test;

import com.moon.spring.bean.Bird;
import com.moon.spring.bean.Cat;
import com.moon.spring.bean.Chocolate;
import com.moon.spring.bean.Parent;
import com.moon.spring.bean.Son;
import com.moon.spring.bean.Student;
import com.moon.spring.config.AppConfig;
import com.moon.spring.util.ContextUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * Spring @Import 注解测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-15 23:06
 * @description
 */
public class ImportTest {

    private final ApplicationContext context = ContextUtils.getAnnotationContext(AppConfig.class);

    @Test
    public void testImportBasic() {
        // @Import直接导入的类
        Cat cat = context.getBean(Cat.class);
        Bird bird = context.getBean(Bird.class);
        System.out.println(cat);
        System.out.println(bird);

        // @Import导入ImportSelector接口实现导入的类
        Parent parent = context.getBean(Parent.class);
        Son son = context.getBean(Son.class);
        System.out.println(parent);
        System.out.println(son);

        // @Import导入ImportBeanDefinitionRegistrar接口实现导入的类
        Student student = context.getBean(Student.class);
        System.out.println(student);
    }

    @Test
    public void testDeferredImportSelectorBasic() {
        Chocolate bean = context.getBean(Chocolate.class);
        System.out.println(bean);
        /*
         * 调用方法的时序如下
         * ImportSelectorDemo.selectImports() 方法执行了....
         * DeferredImportSelectorGroupImpl.process()方法执行了....
         * DeferredImportSelectorDemo.selectImports()方法执行了....
         * DeferredImportSelectorGroupImpl.selectImports()方法执行了....
         */
    }

}
