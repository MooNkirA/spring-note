package com.moon.springsample.custom;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.beans.Introspector;
import java.util.Map;
import java.util.Set;

/**
 * 自定义BeanName生成规则实现类，需实现spring框架的BeanNameGenerator接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-9 23:01
 * @description
 */
public class CustomBeanNameGenerator implements BeanNameGenerator {

    /* Component注解的全类名 */
    private static final String COMPONENT_ANNOTATION_CLASSNAME = "org.springframework.stereotype.Component";

    /* 自定义beanName前缀 */
    private static final String NAME_PREFIX = "MooN_";

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        // 0. 定义返回的beanName
        String beanName = null;

        // 1. 判断当前BeanDefinition对象是否为注解
        if (definition instanceof AnnotatedBeanDefinition) {
            // 2. 将BeanDefinition对象强转成注解的BeanDefinition对象
            AnnotatedBeanDefinition abd = (AnnotatedBeanDefinition) definition;
            // 3. 通过BeanDefinition对象获取注解的元信息（AnnotationMetadata）
            AnnotationMetadata amd = abd.getMetadata();
            // 4. 获取元信息中所有注解的Set集合
            Set<String> annotationTypes = amd.getAnnotationTypes();
            // 5. 遍历AnnotationType的集合
            for (String type : annotationTypes) {
                // 6. 获取注解的属性，（AnnotationAttributes是继承LinkedHashMap）
                AnnotationAttributes attributes = AnnotationAttributes
                        .fromMap(amd.getAnnotationAttributes(type, false));
                // 7. 判断注解属性attributes的是否为空，并且必须是@Component注解或者其衍生注解
                if (attributes != null && isStereotypeWithNameValue(type, amd.getMetaAnnotationTypes(type), attributes)) {
                    // 8. 获取注解中value属性的值
                    Object value = attributes.get("value");
                    if (value instanceof String) {
                        // 9. 如果是字符串类型，强转
                        String strVal = (String) value;
                        // 10. 判断value值是否为空
                        if (StringUtils.hasLength(strVal)) {
                            // 11. 如果beanName为空，并且与注解中的value值不相同，抛出异常
                            if (beanName != null && !strVal.equals(beanName)) {
                                throw new IllegalStateException("Stereotype annotations suggest inconsistent " +
                                        "component names: '" + beanName + "' versus '" + strVal + "'");
                            }
                            // 因为是直接参考spring的实现，为了看到自定义beanName生成规则的效果，这里加上前缀，以作区分
                            beanName = NAME_PREFIX + strVal;
                        }
                    }
                }
            }
        }
        // 这里生成默认的名称也加上前缀，以作区分
        return beanName == null ? NAME_PREFIX + buildDefaultBeanName(definition) : beanName;
    }

    /**
     * 用于判断注解是否为@Component注解或者其衍生注解
     *
     * @param annotationType
     * @param metaAnnotationTypes
     * @param attributes
     * @return
     */
    private boolean isStereotypeWithNameValue(String annotationType, Set<String> metaAnnotationTypes,
                                              @Nullable Map<String, Object> attributes) {

        boolean isStereotype = annotationType.equals(COMPONENT_ANNOTATION_CLASSNAME) ||
                metaAnnotationTypes.contains(COMPONENT_ANNOTATION_CLASSNAME) ||
                annotationType.equals("javax.annotation.ManagedBean") ||
                annotationType.equals("javax.inject.Named");

        return (isStereotype && attributes != null && attributes.containsKey("value"));
    }

    /**
     * 创建一个默认的beanName(spring的原方法)
     *
     * @param definition
     * @return
     */
    private String buildDefaultBeanName(BeanDefinition definition) {
        String beanClassName = definition.getBeanClassName();
        Assert.state(beanClassName != null, "No bean class name set");
        String shortClassName = ClassUtils.getShortName(beanClassName);
        return Introspector.decapitalize(shortClassName);
    }

}
