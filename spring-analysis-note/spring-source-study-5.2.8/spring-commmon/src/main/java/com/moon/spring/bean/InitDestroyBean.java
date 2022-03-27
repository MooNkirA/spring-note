package com.moon.spring.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于测试 xml配置<bean>标签中 init-method 与 destroy-method 属性
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-3 16:12
 * @description
 */
public class InitDestroyBean {
    
    /* 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(InitDestroyBean.class);

    public InitDestroyBean() {
        LOGGER.info("********* InitDestroyBean类的无参构造方法执行了 *********");
    }

    public void initMehtod() {
        LOGGER.info("********* InitDestroyBean.initMehtod() 初始化方法执行了 *********");
    }
    
    public void destroyMethod(){
        LOGGER.info("********* InitDestroyBean.destroyMethod() 销毁方法执行了 *********");
    }
    
    
}
