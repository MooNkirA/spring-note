package com.moon.spring.test;

import com.moon.spring.bean.BeanToAdd;
import com.moon.spring.bean.BeanToDelete;
import com.moon.spring.bean.BeanToEdit;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanDefinitionRegistryPostProcessor 接口使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-13 23:00
 * @description
 */
public class BeanDefinitionRegistryPostProcessorTest {

    /* BeanDefinition 新增、修改、邮件测试 */
    @Test
    public void testBeanDefinitionCRUD() {
        // 读取spring类路径下的配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        BeanToAdd beanToAdd = context.getBean("beanToAdd", BeanToAdd.class);
        System.out.println(beanToAdd);
        BeanToEdit beanToEdit = context.getBean("beanToEdit", BeanToEdit.class);
        System.out.println(beanToEdit);

        try {
            BeanToDelete beanToDelete = context.getBean("beanToDelete", BeanToDelete.class);
            System.out.println(beanToDelete);
        } catch (BeansException e) {
            System.out.println(e.getMessage());
        }
    }

}
