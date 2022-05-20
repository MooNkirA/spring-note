package cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用cglib，基于子类的动态代理
 *
 * @author MoonZero
 */
public class Client_Cglib {

    public static void main(String[] args) {
        // 获取被代理对象
        Actor actor = new Actor();

        /*
         * 来自cglib 要求： 被代理对象不能是最终类
         * 涉及的类：Enhancer
         * 涉及方法：create
         * 涉及参数：
         *        Class : 与被代理对像的字节码对象
         *        CallBack：接口来的，里面写的也是策略
         *                  要使用一个子接口：MethodInterceptor
         */
        Actor cglibActor = (Actor) Enhancer.create(actor.getClass(), new MethodInterceptor() {
            /*
             * 此方法也具有拦截功能
             *
             * 前面三个参数与基于接口的动态代理的InvocationHandler中的invoke方法的参数一模一样
             *  参数解释：
             *          proxy:代理对象的引用【一般不用】
             *          method:拦截的方法
             *          args: 拦截的方法中的参数
             *          methodProxy: 方法代理对象的引用，它可以避免反射调用
             */
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
                    throws Throwable {
                // 获取执行的方法名
                String methodName = method.getName();
                // 获取调用的方法的参数，知道只有一个参数
                float money = (float) args[0];
                // 定义返回对象
                Object result = null;

                // 判断执行的方法
                if ("basicAct".equals(methodName)) {
                    // 对象调用了basicAct方法
                    if (money > 1000F) {
                        // 满足条件才执行方法
                        // result = method.invoke(actor, money / 2);
                        // 内部没有用反射, 需要目标，spring 框架采用这种方式
                        result = methodProxy.invoke(actor, new Object[]{money / 2});
                        // 内部没有用反射, 需要代理
                        // result = methodProxy.invokeSuper(proxy, new Object[]{money / 2});
                    }
                } else if ("wonderfulAct".equals(methodName)) {
                    // 对象调用了wonderfulAct方法
                    if (money > 2000F) {
                        // 满足条件才执行方法
                        // result = method.invoke(actor, money / 4);
                        // 内部没有用反射, 需要目标，spring 框架采用这种方式
                        result = methodProxy.invoke(actor, new Object[]{money / 4});
                        // 内部没有用反射, 需要代理
                        // result = methodProxy.invokeSuper(proxy, new Object[]{money / 4});
                    }
                }
                // 返回执行方法后的对象
                return result;
            }
        });

        // 使用代理调用方法
        cglibActor.basicAct(1200F);
        cglibActor.wonderfulAct(4290F);
    }
}
