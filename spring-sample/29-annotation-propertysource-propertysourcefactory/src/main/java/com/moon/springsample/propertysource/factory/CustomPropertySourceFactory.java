package com.moon.springsample.propertysource.factory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * 自定义解析yaml文件的工厂类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-30 10:41
 * @description
 */
public class CustomPropertySourceFactory implements PropertySourceFactory {

    /**
     * 自定义解析规则，该方法主要实现的逻辑是将方法的入参EncodedResource转成PropertySource对象即可
     * 引入第三yaml文件解析器
     *
     * @param name
     * @param resource
     * @return
     * @throws IOException
     */
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        // 1. 创建yaml文件解析工厂
        YamlPropertiesFactoryBean yamlFactoryBean = new YamlPropertiesFactoryBean();
        // 2. 设置要解析的内容
        yamlFactoryBean.setResources(resource.getResource());
        // 3. 将资源解析成properties文件
        Properties properties = yamlFactoryBean.getObject();
        /*
         * 4. 转成PropertySource对象返回
         *  原DefaultPropertySourceFactory中是通过ResourcePropertySource来创建PropertySource对象，但构造函数的入参不是Properties对象
         *  按源码往父类构造方法去寻找，此时发现父类PropertiesPropertySource的构造方法入参为Properties对象，
         *  所以通过PropertiesPropertySource来创建PropertySource对象，但PropertiesPropertySource只有一个两个参数的构造函数
         *  调用时只能修改逻辑为如果没有，则与资源文件名称做为入参name的值
         */
        String propertyName = name != null ? name : resource.getResource().getFilename();
        return new PropertiesPropertySource(propertyName, properties);
    }

}
