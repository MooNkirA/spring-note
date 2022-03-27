package com.moon.spring.aop.targetsource;

import org.springframework.aop.target.AbstractBeanFactoryBasedTargetSource;

/**
 * 自定义 TargetSource 接口实现
 * <p>
 * 1. 可以直接实现TargetSourceC接口，重写getTarget方法（还有其他方法需要实现）
 * 2. 也可以继承AbstractBeanFactoryBasedTargetSource抽象类，
 * 该类也是实现了TargetSource接口，此方式只需要实现getTarget方法即可
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-3-6 16:40
 * @description
 */
public class CustomTargetSource extends AbstractBeanFactoryBasedTargetSource {

    @Override
    public Object getTarget() throws Exception {
        // 从BeanFactory中根据bean名称返回实例
        return getBeanFactory().getBean(getTargetBeanName());
    }

}

/*public class CustomTargetSource implements TargetSource {

    @Override
    public Class<?> getTargetClass() {
        return LogServiceImpl.class;
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public Object getTarget() throws Exception {
        return getTargetClass().newInstance();
    }

    @Override
    public void releaseTarget(Object target) throws Exception {

    }

}*/
