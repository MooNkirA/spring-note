package com.moon.springsample.config;

import com.moon.springsample.annotation.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-29 08:41
 * @description
 */
@Configuration
public class SpringConfiguration {

    /* @Bean注解的autowireCandidate属性，不会影响 @Resource 注解的注入*/
    @Resource(name = "dataSource")
    private DataSource dataSource;

    /**
     * 创建一个数据源对象，并使用 @Bean 注解存入 spring ioc 容器
     *
     * @return DataSource
     */
    // name属性用于指定存入spring容器中bean的标识。支持指定多个标识。当不指定该属性时，默认值是当前方法的名称。
    // @Bean(name = "dataSource")
    // valeu属性是4.3.3版本之后加入，与name属性作用一样，当前@Bean注解没有设置其他属性值时，value可以省略
    // @Bean(value = "dataSource")
    // autowireCandidate用于指定是否支持自动按类型注入到其他bean中。只影响`@Autowired`注解的使用。不影响`@Resource`注解注入。默认值为true
    @Bean(value = "dataSource", autowireCandidate = true)
    public DataSource createDataSource() {
        // 1. 创建对象
        DataSource dataSource = new DriverManagerDataSource();
        /*
         * 2. 对象初始化的工作
         *   注：这里虽然可以使用@Bean注解的initMethod属性来单独编写方法来实现此步骤
         *   但一般比较少用，建议使用编程式试直接在创建对象的同时做初始化的工作
         */
        // ....
        // 3. 返回对象到ioc容器
        return dataSource;
    }

    /* 使用@Autowired自动注入ioc容器对象 */
    // @Bean("jdbcTemplate")
    // 如果返回DataSource对象的方法的@Bean注解设置属性autowireCandidate = false，则通过@Autowired注解无法自动注入DataSource对象
    // public JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource) {
    // 但可以使用@Resource注解注入到成员变量的方式实现
    // 如果不指定name/value属性，则存入ioc容器时，以方法的名称作为bean的名称标识
    @Bean
    public JdbcTemplate createJdbcTemplate() {
        System.out.println("执行了无参的createJdbcTemplate()方法");
        return new JdbcTemplate(dataSource);
    }

    // 如果不指定name/value属性，并出现了方法重载，则以最后定义的方法的返回对象，注入到spring ioc容器中
    // 使用@Bean做为元注解，自定义@MyBean注解，实现功能与@Bean一致
    @MyBean
    public JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource) {
        System.out.println("执行了有参的createJdbcTemplate()方法");
        return new JdbcTemplate(dataSource);
    }

}
