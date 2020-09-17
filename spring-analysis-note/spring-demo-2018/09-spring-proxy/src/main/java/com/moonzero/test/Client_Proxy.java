package com.moonzero.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.moonzero.entity.IActor;
import com.moonzero.entity.impl.ActorImpl;

/**
 * 使用JDK官方的Proxy类创建代理对象
 *
 * @author MoonZero
 */
public class Client_Proxy {
    public static void main(String[] args) {
        // 获取接口实现类
        IActor actor = new ActorImpl();

        System.out.println("=============没有使用动态代理模式前=============");
        actor.basicAct(108.89F);
        actor.wonderfulAct(3000.1F);
        System.out.println("=============没有使用动态代理模式后=============");

        // 获取代理
        IActor proxy = (IActor) Proxy.newProxyInstance(ActorImpl.class.getClassLoader(),
                ActorImpl.class.getInterfaces(), new InvocationHandler() {
                    // 重写拦截的方法
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 获取调用方法的名字
                        String mothodName = method.getName();
                        // 获取调用方法的参数
                        float money = (float) args[0];

                        // 开始判断
                        if ("basicAct".equals(mothodName)) {
                            // 对象调用了basicAct方法
                            if (money > 2000) {
                                // 满足条件才执行方法
                                proxy = method.invoke(actor, money / 2);
                            }
                        } else if ("wonderfulAct".equals(mothodName)) {
                            // 对象调用了wonderfulAct方法
                            if (money > 5000) {
                                // 满足条件才执行方法
                                proxy = method.invoke(actor, money / 2);
                            }
                        }
                        // 真实方法返回值
                        return proxy;
                    }
                });

        // 使用代理调用方法
        proxy.basicAct(1003F);
        proxy.wonderfulAct(6234F);
    }
}
