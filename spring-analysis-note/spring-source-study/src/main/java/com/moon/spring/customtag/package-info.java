/**
 * 自定义标签解析Demo
 * <p>步骤如下：</p>
 * <p>1. 创建自定义标签解析类，继承AbstractSingleBeanDefinitionParser抽象类，
 * 实现doParse与getBeanClass方法，在getBeanClass方法里返回需要实例化的类字节码对象；
 * 在doParse方法中设置标签的属性值</p>
 *
 * <p>2. 创建NamespaceHandler类，继承NamespaceHandlerSupport抽象类，实现init方法，
 * 在init方法里注册标签对应的解析类</p>
 *
 * <p>3. 在resources/META-INF目录下分别创建spring.handlers、spring.schemas、xxx.xsd标签的约束文件</p>
 * <p>spring.handlers文件中定义了namespace与处理类的映射</p>
 * <p>spring.schemas文件中定义了namespace与约束文件的映射</p>
 */
package com.moon.spring.customtag;