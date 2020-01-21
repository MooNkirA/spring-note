package com.moon.spring.customtag;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;
import redis.clients.jedis.Jedis;

/**
 * 自定义标签解析类，继承Spring框架的AbstractSingleBeanDefinitionParser，
 * <p>利用spring提供的抽象父类去解析自定义标签</p>
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-11 17:11
 * @description
 */
public class RedisBeanDifinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Jedis.class;
    }


    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        // 获取自定义标签元素的值
        String ip = element.getAttribute("ip");
        String port = element.getAttribute("port");

        // 通过BeanDefinition对象的ConstructorArgValue的属性，将标签元素的值设置到Jedis的一个构造方法中
        builder.addConstructorArgValue(ip);
        builder.addConstructorArgValue(Integer.parseInt(port));
    }

}
