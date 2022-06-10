package com.moon.spring.config;

import com.moon.spring.annotation.CustomAnnotation;
import com.moon.spring.bean.Bird;
import com.moon.spring.bean.Cat;
import com.moon.spring.constant.Constants;
import com.moon.spring.registrar.DeferredImportSelectorDemo;
import com.moon.spring.registrar.ImportBeanDefinitionRegistrarDemo;
import com.moon.spring.registrar.ImportSelectorDemo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Spring 配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-15 22:37
 * @description
 */
@ComponentScan(Constants.BASE_PACKAGES)
// 使用@Import注解导入一个（或多个）类，导入的类不需要再使用@Component相关注解即可
@Import({Bird.class, Cat.class, ImportSelectorDemo.class, ImportBeanDefinitionRegistrarDemo.class, DeferredImportSelectorDemo.class})
// 用于测试导入ImportSelector与ImportBeanDefinitionRegistrar接口实现方法是否以下注解信息
@CustomAnnotation({"MooN", "L", "kirA"})
public class AppConfig {
}
