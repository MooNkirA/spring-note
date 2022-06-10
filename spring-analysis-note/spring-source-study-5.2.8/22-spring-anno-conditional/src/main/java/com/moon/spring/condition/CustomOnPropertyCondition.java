package com.moon.spring.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 当检查类指定的属性具有特定值，才实例化注解标识的类具体条件实现
 * （！待实现）
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-17 20:46
 * @description
 */
public class CustomOnPropertyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return false;
    }

}
