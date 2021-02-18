package com.moon.spring.condition;

import com.moon.spring.annotation.MoonConditionalOnClass;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

/**
 * 当项目存在指定的Class文件时，才实例化注解标识的类具体条件实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-17 16:34
 * @description
 */
public class CustomOnClassCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (metadata.isAnnotated(MoonConditionalOnClass.class.getName())) {
            Class<?>[] classes = metadata.getAnnotations().get(MoonConditionalOnClass.class).getClassArray("value");
            try {
                for (Class<?> clazz : classes) {
                    ClassUtils.forName(clazz.getName(), ClassUtils.getDefaultClassLoader());
                }
                return true;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}