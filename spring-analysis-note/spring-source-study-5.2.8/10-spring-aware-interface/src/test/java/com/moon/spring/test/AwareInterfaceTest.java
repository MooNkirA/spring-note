package com.moon.spring.test;

import com.moon.spring.aware.CustomApplicationContextAware;
import com.moon.spring.aware.CustomBeanClassLoaderAware;
import com.moon.spring.aware.CustomBeanFactoryAware;
import com.moon.spring.aware.CustomBeanNameAware;
import com.moon.spring.aware.CustomEnvironmentAware;
import com.moon.spring.aware.CustomImportAware;
import com.moon.spring.aware.CustomResourceLoaderAware;
import org.junit.Test;
import org.springframework.beans.factory.Aware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring aware 接口测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-3 10:47
 * @description
 */
public class AwareInterfaceTest {

    private final ApplicationContext context = new AnnotationConfigApplicationContext("com.moon.spring");

    /* 启动容器，测试所有Aware接口的实现 */
    @Test
    public void testAllAware() {
        String[] beanNamesForType = context.getBeanNamesForType(Aware.class);
        for (String beanName : beanNamesForType) {
            System.out.println("Aware类型的实现 ==== " + beanName);
        }
    }

    /* BeanNameAware 接口测试 */
    @Test
    public void testBeanNameAware() {
        CustomBeanNameAware customBeanNameAware = context.getBean("customBeanNameAware", CustomBeanNameAware.class);
        System.out.println(customBeanNameAware);
    }

    /* BeanFactoryAware 接口测试 */
    @Test
    public void testBeanFactoryAware() {
        CustomBeanFactoryAware customBeanFactoryAware = context.getBean("customBeanFactoryAware", CustomBeanFactoryAware.class);
        System.out.println(customBeanFactoryAware);
    }

    /* ApplicationContextAware 接口测试 */
    @Test
    public void testApplicationContextAware() {
        CustomApplicationContextAware customApplicationContextAware = context.getBean("customApplicationContextAware", CustomApplicationContextAware.class);
        System.out.println(customApplicationContextAware);
    }

    /* EnvironmentAware 接口测试 */
    @Test
    public void testEnvironmentAware() {
        CustomEnvironmentAware customEnvironmentAware = context.getBean("customEnvironmentAware", CustomEnvironmentAware.class);
        System.out.println(customEnvironmentAware);
    }

    /* BeanClassLoaderAware 接口测试 */
    @Test
    public void testBeanClassLoaderAware() {
        CustomBeanClassLoaderAware customBeanClassLoaderAware = context.getBean("customBeanClassLoaderAware", CustomBeanClassLoaderAware.class);
        System.out.println(customBeanClassLoaderAware);
    }

    /* ImportAware 接口测试 */
    @Test
    public void testImportAware() {
        String[] beanNamesForType = context.getBeanNamesForType(CustomImportAware.class);
        for (String beanName : beanNamesForType) {
            System.out.println(beanName);
        }
        // 注意：通过@Import注解导入的类，在spring容器中注册的名称不是默认的“类名首字母小写”，而是“类的全限定名”
        CustomImportAware customImportAware = context.getBean("com.moon.spring.aware.CustomImportAware", CustomImportAware.class);
        System.out.println(customImportAware);
    }

    /* ResourceLoaderAware 接口测试 */
    @Test
    public void testResourceLoaderAware() {
        CustomResourceLoaderAware customResourceLoaderAware = context.getBean("customResourceLoaderAware", CustomResourceLoaderAware.class);
        System.out.println(customResourceLoaderAware);
        // 调用获取Metadata元数据的方法
        customResourceLoaderAware.getMetadata();
    }


}
