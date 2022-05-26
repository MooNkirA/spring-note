# springmvc-sample 项目

> 注：此示例项目会参考一些网络示例，外加个人了解学习整理

## 项目简介

此项目是 Spring MVC 的使用示例，包含用来深度学习并实战  Spring MVC 的示例，如传统 xml 使用、纯注解驱动开发示例、所涉及的设计模式的demo

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

| Module 名称               | Module 介绍                                                  |
| ------------------------- | ------------------------------------------------------------ |
| sample-common             | Spring 示例项目公共模块                                      |
| 01-spring-mvc-quickstart  | 基于 web.xml 传统配置的 Spring MVC 快速入门                  |
| 02-spring-mvc-ssm         | 基于 web.xml 传统配置的 SSM 整合示例1                        |
| 03-spring-mvc-ssm         | 基于 web.xml 传统配置的 SSM 整合示例2                        |
| 04-spring-mvc-crm         | 基于 web.xml 传统配置的 SSM 整合实现 CRM 系统示例            |
| 05-spring-mvc-annotation  | 基于纯注解实现的 Spring MVC 示例                             |
| 06-spring-mvc-crossorigin | 基于纯注解的 Spring MVC @CrossOrigin 注解处理跨域示例，用于使用js发送ajax请求 |
|                           |                                                              |
