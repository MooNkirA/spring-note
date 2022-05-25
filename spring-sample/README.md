# spring-sample 项目

> 注：此示例项目会参考一些网络示例，外加个人了解学习整理

## 项目简介

此项目是 Spring 的使用示例，包含用来深度学习并实战  Spring 的示例，如传统 xml 使用、纯注解驱动开发示例、所涉及的设计模式的demo

目前示例使用的 **Spring 的版本是 5.3.19**，并依赖了 Spring Boot 框架版本是 2.5.13（为了一些示例引入技术更简便一些）

<font color=red>*项目持续更新中...*</font>

## 项目运行环境

- **JDK 1.8 +**
- **Maven 3.5 +**
- **IntelliJ IDEA ULTIMATE 2018.2 +** (*注意：推荐使用 IDEA 开发，同时保证安装 `lombok` 插件*)
- **Mysql 5.7 +** (*尽量保证使用 5.7 版本以上，因为 5.7 版本加了一些新特性，同时不向下兼容。示例可能不确认兼容性*)

## 项目运行方式

1. `git clone https://github.com/MooNkirA/spring-note.git`
2. 使用 IDEA 打开 clone 下来的项目
3. 在 IDEA 中 Maven Projects 的面板导入项目根目录下 的 `pom.xml` 文件（勾上 IDEA 顶部工具栏的 View -> Tool Buttons ，然后 Maven Projects 的面板就会出现在 IDEA 的右侧）
4. **注意：有些 demo 可能会有 README 配套，可配合代码食用~**
5. **注意：运行某些 demo 之前，可能有需要事先初始化数据库数据的，别忘记了哦~**

## 项目模块介绍

| Module 名称                                        | Module 介绍                                          |
| -------------------------------------------------- | ---------------------------------------------------- |
| sample-common                                      | Spring 示例项目公共模块                              |
| sample-common                                      |                                                      |
| 01-spring-factory                                  |                                                      |
| 02-spring-basic                                    |                                                      |
| 03-spring-inject                                   |                                                      |
| 04-spring-annotation                               |                                                      |
| 05-spring-annotation-no-xml                        |                                                      |
| 06-spring-annotation-import-propertysource         |                                                      |
| 07-spring-junit-xml                                |                                                      |
| 08-spring-junit-annotation                         |                                                      |
| 09-spring-proxy                                    | Jdk 动态代理与 CGlib 动态代理示例                    |
| 10-spring-aop-xml                                  |                                                      |
| 11-spring-aop-annotation                           |                                                      |
| 12-spring-aop-annotation-noXML                     |                                                      |
| 13-spring-aop-hibernate                            |                                                      |
| 14-spring-aop-hibernate-annotation                 |                                                      |
| 15-spring-jdbcTemplate                             |                                                      |
| 16-spring-jdbcTemplateOnDao                        |                                                      |
| 17-spring-jdbcTemplateOnDao2                       |                                                      |
| 18-spring-tx                                       |                                                      |
| 19-spring-tx-annotation                            |                                                      |
| 20-spring-annotation-quickstart                    |                                                      |
| 21-annotation-configuration                        |                                                      |
| 22-annotation-componentscan                        |                                                      |
| 23-annotation-componentscan-filter                 |                                                      |
| 24-annotation-bean                                 |                                                      |
| 25-annotation-import                               |                                                      |
| 26-annotation-import-importselector                |                                                      |
| 27-annotation-import-importbeandefinitionregistrar |                                                      |
| 28-annotation-propertysource                       |                                                      |
| 29-annotation-propertysource-propertysourcefactory |                                                      |
| 30-annotation-dependson                            |                                                      |
| 31-annotation-lazy                                 |                                                      |
| 32-annotation-conditional                          |                                                      |
| 33-annotation-profile                              |                                                      |
| 34-annotation-component-composite-sample           |                                                      |
| 35-annotation-autowired                            |                                                      |
| 36-annotation-value                                |                                                      |
| 37-annotation-jsr-resource-inject                  |                                                      |
| 38-annotation-primary                              |                                                      |
| 39-annotation-lifecycle                            | Spring Bean 生命周期各种回调实现示例                 |
| 40-spring-aop-proxy                                |                                                      |
| 41-annotation-aop-quickstart                       | Spring 基于注解方式实现 AOP 示例                     |
| 41-aspectj-aop-extension-ajc                       | 基于 ajc 编译器实现 AOP 扩展示例                     |
| 41-aspectj-aop-extension-agent                     | 基于 agent 类加载实现 AOP 扩展示例                   |
| 42-annotation-aop-enableaspectjautoproxy           |                                                      |
| 43-annotation-aop-aspect                           |                                                      |
| 44-annotation-aop-pointcut                         |                                                      |
| 45-annotation-aop-advicetype-general               |                                                      |
| 46-annotation-aop-advicetype-sequence              |                                                      |
| 47-annotation-aop-around                           |                                                      |
| 48-annotation-aop-declareparents                   |                                                      |
| 49-annotation-aop-enableloadtimeweaving            |                                                      |
| 50-spring-tx-jdbctemplate-quickstart               |                                                      |
| 51-spring-tx-custom-jdbctemplate                   |                                                      |
| 52-spring-tx-custom-jdbctemplate-test              |                                                      |
| 53-annotation-tx-transaction-quickstart            |                                                      |
| 54-spring-beanfactory-applicationcontext           | Spring 的 BeanFactory 与 ApplicationContext 接口示例 |
| 55-spring-beanpostprocessor                        | Spring 容器扩展点之 BeanPostProcessor 接口（未完成） |
| 56-spring-beanfactorypostprocessor                 | Spring 容器扩展点之 BeanFactoryPostProcessor 接口    |
| 57-spring-factorybean                              | Spring 容器扩展点之 FactoryBean（未开始）            |
| 58-spring-aware-interfaces                         | Spring 的 Aware 系列接口基础示例使用（陆续完善）     |
| 59-spring-bean-scopes                              | Spring Bean 作用范围示例                             |
| 60-spring-aop-analysis                             | Spring 基于编程式实现 AOP 示例与功能实现分析         |