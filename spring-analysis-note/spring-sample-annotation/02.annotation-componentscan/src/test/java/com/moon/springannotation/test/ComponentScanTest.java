package com.moon.springannotation.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.service.AccountService;
import com.moon.springsample.service.UserService;
import com.moon.springsample.utils.LogUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ComponentScan注解测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-2 12:17
 * @description
 */
public class ComponentScanTest {

    @Test
    public void componentScanBaseTest() {
        // 1. 获取基于注解的spinrg容器，使用传入字节码的构造函数创建容器。（这里故意不使用传入基础包的构造函数，如果这里配置了扫描包包含了测试层的位置，则看不出效果）
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2. 根据id或者类型去获取对应的bean实例
        UserService userService = context.getBean("userService", UserService.class);
        // 3. 调用对象方法
        userService.saveUser();
    }

    @Test
    public void componentScanBasePackagesTest() {
        // 1. 获取基于注解的spinrg容器，使用传入字节码的构造函数创建容器。（这里故意不使用传入基础包的构造函数，如果这里配置了扫描包包含了测试层的位置，则看不出效果）
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2. 根据id或者类型去获取对应的bean实例
        UserService userService = context.getBean("userService", UserService.class);
        // 3. 调用对象方法
        userService.saveUser();
    }

    /* 测试basePackageClasses属性 */
    @Test
    public void componentScanBasePackageClassesTest() {
        // 1. 获取基于注解的spinrg容器，使用基础包的构造函数，只扫描配置类所在的包。
        ApplicationContext context = new AnnotationConfigApplicationContext("com.moon.springsample.config");
        // 2. 根据id或者类型去获取对应的bean实例
        UserService userService = context.getBean("userService", UserService.class);
        // 3. 调用对象方法
        userService.saveUser();

        // 使用basePackageClasses方法扫描，测试指定字节码类所在的包及其子包所有的类是否被扫描到
        AccountService accoutService = context.getBean("accountService", AccountService.class);
        accoutService.deleteAccount();
    }

    /* 测试nameGenerator属性 */
    @Test
    public void componentScanNameGeneratorTest() {
        // 1. 获取基于注解的spinrg容器，使用基础包的构造函数，只扫描配置类所在的包。
        ApplicationContext context = new AnnotationConfigApplicationContext("com.moon.springsample.config");
        // 2. 根据id去获取对应的bean实例，因为是自定义baeanName命名规则，所以需要使用自定义的beanName才能获取到相应的spring容器的对象
        UserService userService = context.getBean("MooN_userService", UserService.class);
        // 3. 调用对象方法
        userService.saveUser();

        // 使用spring架构默认的命名规则的名称去获取容器中的实例，报错！
        AccountService accoutService = context.getBean("accountService", AccountService.class);
        accoutService.deleteAccount();
    }

    /* 测试resourcePattern属性 */
    @Test
    public void componentScanNameResourcePatternTest() {
        // 1. 获取基于注解的spinrg容器，使用基础包的构造函数，只扫描配置类所在的包。
        ApplicationContext context = new AnnotationConfigApplicationContext("com.moon.springsample.config");
        // 2. 因为是配置了resourcePattern属性为"*/*.class"，所以扫描"com.moon.springsample"下任意包的任意class文件，所以扫描不到service包下的impl包中的注解
        UserService userService = context.getBean("userService", UserService.class);
        // 3. 因为没有扫描到实现类的注解，所有无法加入到spring容器中，对象为null，调用对象方法时报错
        userService.saveUser();
    }

    /* 测试includeFilters、excludeFilters属性 */
    @Test
    public void componentScanFiltersTest() {
        // 1. 获取基于注解的spinrg容器，使用基础包的构造函数，只扫描配置类所在的包。
        ApplicationContext context = new AnnotationConfigApplicationContext("com.moon.springsample.config");
        /*
         *  配置includeFilters属性为@ComponentScan.Filter(value = Service.class)，
         *      代表过滤规则包含@Service注解，不会影响其他注解的扫描，如LogUtil类上的@Component注解
         *  配置excludeFilters属性为@ComponentScan.Filter(value = Service.class)，
         *      代表过滤规则会排除@Service注解，有该注解的类不会被扫描也不会加入到spring容器，如UserService类上的@Service注解
         */
        UserService userService = context.getBean("userService", UserService.class);
        LogUtil logUtil = context.getBean("logUtil", LogUtil.class);

        // 3. 如果配置excludeFilters排除@Service注解后，执行程序会报[No bean named 'userService' available]的错误
        userService.saveUser();
        logUtil.printLog();
    }

}
