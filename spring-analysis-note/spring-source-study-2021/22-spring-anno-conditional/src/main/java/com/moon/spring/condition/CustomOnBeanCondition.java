package com.moon.spring.condition;

import com.moon.spring.annotation.MoonConditionalOnBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotationCollectors;
import org.springframework.core.annotation.MergedAnnotationPredicates;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

/**
 * 当指定的Bean存在时实例化被注解标识的类具体条件实现(暂未实现)
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-17 16:33
 * @description
 */
public class CustomOnBeanCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 判断是否标识相应的注解
        if (metadata.isAnnotated(MoonConditionalOnBean.class.getName())) {
            // 获取MoonConditionalOnBean类上其他
            MergedAnnotations annotations = metadata.getAnnotations();

            // .class类型转string（只是API示例，此方法没有用到）
            MultiValueMap<String, Object> attributes = annotations
                    .stream(MoonConditionalOnBean.class)
                    .filter(MergedAnnotationPredicates.unique(MergedAnnotation::getMetaTypes))
                    .collect(MergedAnnotationCollectors.toMultiValueMap(MergedAnnotation.Adapt.CLASS_TO_STRING));

            // 获取@MoonConditionalOnBean注解的所有属性值
            MergedAnnotation<MoonConditionalOnBean> cob = annotations.get(MoonConditionalOnBean.class);
            // 获取配置的value值
            Class<?>[] values = cob.getClassArray("value");

            // 获取bean实例工厂
            ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
            // 循环有类字节码数组
            for (Class<?> clazz : values) {
                // 根据字节码获取容器中的beanName
                String[] beanNames = beanFactory.getBeanNamesForType(clazz);
                // 容器不存在，则返回false
                if (beanNames.length == 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
