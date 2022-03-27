package com.moon.spring.postprocessor;

import com.moon.spring.bean.BeanToAdd;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * 自定义 BeanDefinitionRegistryPostProcessor 实现基础功能示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-12 22:44
 * @description
 */
// PriorityOrdered（排序，优先级）接口是用于Spring创建同一类型的Bean时进行排序
@Component
public class CustomBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

    /**
     * Modify the application context's internal bean definition registry after its
     * standard initialization. All regular bean definitions will have been loaded,
     * but no beans will have been instantiated yet. This allows for adding further
     * bean definitions before the next post-processing phase kicks in.
     *
     * @param registry the bean definition registry used by the application context
     *                 这是Spring框架的BeanDefinition的注册器，此注册器可以获取所有spring容器管理的BeanDefinition对象
     * @throws BeansException in case of errors
     */
    /*
     * 在spring容器加载，加载xml配置文件解析（注解扫描），生成所有BeanDefinition 后，在bean实例化前的执行此方法
     * 所以可以使用此接口方法手动 BeanDefinition 的动态修改，完成对Spring容器里面所有 BeanDefinition 对象的新增、修改、删除、查询操作
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 通过BeanDefinitionRegistry注册容器，可以查询所有已注册的BeanDefinition
        final String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
            System.out.println(beanDefinition);
        }

        /* *************************** 手动新增BeanDefinition注册 *************************************/
        // 创建GenericBeanDefinition对象
        GenericBeanDefinition bdToAdd = new GenericBeanDefinition();
        // 设置需要实例化的类
        bdToAdd.setBeanClass(BeanToAdd.class);

        // 如果需要实例化的类中属性赋值，需要获取MutablePropertyValues属性，赋值到此属性中
        MutablePropertyValues propertyValues = bdToAdd.getPropertyValues();
        propertyValues.addPropertyValue("value", "实现BeanDefinitionRegistryPostProcessor接口，手动创建BeanDefinition对象并注册到spring容器中");

        // 将BeanDefinition对象注册到spring容器中，spring实例化对象，必须将beanName与BeanDefinition对象进行映射。（即添加到beanDefinitionMap属性中）
        registry.registerBeanDefinition("beanToAdd", bdToAdd);

        /* *************************** 手动修改原已注册的BeanDefinition *************************************/
        // 从BeanDefinitionRegistry中获取BeanDefinition对象
        BeanDefinition bdToEdit = registry.getBeanDefinition("beanToEdit");

        // 如果需要实例化的类中属性赋值，需要获取MutablePropertyValues属性，赋值到此属性中
        MutablePropertyValues bdToEditPropertyValues = bdToEdit.getPropertyValues();
        bdToEditPropertyValues.addPropertyValue("value", "我是通过实现BeanDefinitionRegistryPostProcessor接口后修改的值");

        /* *************************** 手动删除原已注册的BeanDefinition *************************************/
        // 根据beanName删除BeanDefinition
        registry.removeBeanDefinition("beanToDelete");

    }

    /**
     * Modify the application context's internal bean factory after its standard
     * initialization. All bean definitions will have been loaded, but no beans
     * will have been instantiated yet. This allows for overriding or adding
     * properties even to eager-initializing beans.
     *
     * @param beanFactory the bean factory used by the application context
     * @throws BeansException in case of errors
     */
    /*
     * 此方法是父接口BeanFactoryPostProcessor的方法
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        /*
         * BeanFactory 对象一样可以拿到所有的BeanDefinition对象，因为BeanFactory的相关实现类也都会实现 BeanDefinitionRegistry 接口
         */
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
        // 从注册中心获取所有注册的BeanDefinition的名称
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();

        for (String bdName : beanDefinitionNames) {
            System.out.println("BeanDefinition的名称" + bdName);
            System.out.println(registry.getBeanDefinition(bdName));
        }

        /* 修改BeanFactory相关的参数 */
        DefaultListableBeanFactory beanFactory1 = (DefaultListableBeanFactory) beanFactory;
        beanFactory1.setAllowBeanDefinitionOverriding(true);
        beanFactory1.setAllowCircularReferences(true);
        beanFactory1.setAllowRawInjectionDespiteWrapping(true);
    }

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        // Spring根据此数值在创建Bean时，进行排序。数值越少越优先
        return 0;
    }
}
