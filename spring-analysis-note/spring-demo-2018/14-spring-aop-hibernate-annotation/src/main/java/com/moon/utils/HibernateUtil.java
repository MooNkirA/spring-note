package com.moon.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

/**
 * hibernate 工具类
 *
 * @author MoonZero
 */
// 配置当前类给spring容器管理
@Component("util")
// 设置当前类为切面
@Aspect
public class HibernateUtil {
    // 使用static的关键字，整个项目共享一个对象。
    // 如果一个项目里面出现多个连接池，有可能导致事务处理不同步。事务同步提交的前提是同一个连接。
    // 创建静态Session工厂成员变量
    public static SessionFactory sessionFactory;
    // 创建线程共享对象
    public static ThreadLocal<Session> local = new ThreadLocal<Session>();

    /**
     * 定义切入点
     */
    @Pointcut("execution(* *..UserServiceImpl.*(..))")
    public void pt1() {
    }

    // 使用静态获得会话工厂
    static {
        // 1.创建Configuration对象
        Configuration cfg = new Configuration();
        // 2.读取配置文件，获得框架信息，默认读取classpath根目录hibernate.cfg.xml
        cfg.configure();
        // 3.获得会话工厂,如果要连接数据库必须需要连接数据库的信息（四要素）
        // 获得会话工厂必须要在读取配置文件之后
        sessionFactory = cfg.buildSessionFactory();
    }

    // 获取会话操作对象静态方法
    public static Session getSession() {
        // 先判断共享线程对象是否有值
        if (local.get() == null) {
            // 使用工厂获取Session对象
            Session session = sessionFactory.openSession();
            local.set(session);
        }
        // 返回共享线程对象中值
        return local.get();
    }

    // 关闭会话操作对象和清空共享线程对象,最终通知
    @After("pt1()")
    public static void close() {
        // 判断共享线程对象是否有值
        if (local.get() != null) {
            // 关闭资源
            local.get().close();
            local.remove();
        }
        System.out.println("最终通知");
    }

    // 开启事务,前置通知
    @Before("pt1()")
    public static void beginTransaction() {
        getSession().beginTransaction();
        System.out.println("前置通知");
    }

    // 提交事务,后置通知
    @AfterReturning("pt1()")
    public static void commit() {
        getSession().beginTransaction().commit();
        System.out.println("后置通知");

    }

    // 回滚事务,异常通知
    @AfterThrowing("pt1()")
    public static void rollback() {
        getSession().beginTransaction().rollback();
    }

    // 环绕事务
    // @Around("pt1()")
    public Object aroundTransaction(ProceedingJoinPoint pjp) {
        // 获取方法列表参数
        Object[] args = pjp.getArgs();
        // 定义返回值变量
        Object result = null;

        try {
            // 前置通知
            beginTransaction();
            // 手动调用方法
            result = pjp.proceed(args);
            // 后置通知
            commit();
        } catch (Throwable e) {
            e.printStackTrace();
            // 异常通知
            rollback();
        } finally {
            // 最终通知
            close();
        }
        // 返回值
        return result;
    }

    // 测试是否连接成功
    public static void main(String[] args) {
        // 获取session操作对象
        Session session = getSession();
        System.out.println(session);
        close();
    }
}