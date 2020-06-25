package com.moon.spring.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 构造方法自动注入示例
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-2-15 18:54
 * @description
 */
@Data
@Service
public class ConstructorAutowiredBean {

    private Student student;

    /**
     * 使用@Autowired注解方式自动注入，给成员变量赋值
     * <p>
     * 相当于以前在xml文件中配置<bean>标签中的<constructor-arg>子标签
     *
     * @param student
     */
    @Autowired
    public ConstructorAutowiredBean(Student student) {
        this.student = student;
    }
}
