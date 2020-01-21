package com.moon.spring.customtag;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Node;

/**
 * 自定义标签处理类，继承Spring框架的NamespaceHandlerSupport类
 * <p>实现init方法，进行自定义标签的初始化注册，将相应的标签解析注册到容器中</p>
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-11 17:19
 * @description
 */
public class TagsNamespaceHandler extends NamespaceHandlerSupport {

    /* 注册自定义标签解析类 */
    @Override
    public void init() {
        this.registerBeanDefinitionParser("redis", new RedisBeanDifinitionParser());
    }

    @Override
    public BeanDefinitionHolder decorate(Node node, BeanDefinitionHolder definition, ParserContext parserContext) {
        return super.decorate(node, definition, parserContext);
    }

}
