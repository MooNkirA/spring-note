package com.moon.spring.lambda;

/**
 * Lambda表达式，不要关心接口是什么，接口里面方法是什么，只需要关心方法里面写什么代码就可以了
 * 方法引用：不要关心接口是什么，接口里面方法是什么，只要关心如何调用方法就可以了
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-5-3 10:43
 * @description
 */
public class LambdaDemo {

    public static void main(String[] args) {

        /* 使用lambda表达式创建接口实现 */
        FunctionInterface fun = x -> 1;

        /* 等价于JDK8以前的匿名内部类的方式 */
        FunctionInterface fun1 = new FunctionInterface() {
            @Override
            public int doSomething(String s) {
                return 1;
            }
        };

        /*  调用函数式接口相关的方法 */
        // 调用实现的方法
        System.out.println("我是抽象方法===" + fun.doSomething(""));
        // 调用默认方法
        fun.defaultMethod();
        // 调用重写Object里的public方法
        System.out.println("我是Object里的public方法===" + fun.equals(1));
        // 调用静态方法
        FunctionInterface.staticMethod();

        /*
         * 方法引用是Lambda表达式的一个简化写法。所引用的方法其实是Lambda表达式的方法体的实现。
         * 如果正好有某个方法满足一个lambda表达式的形式，那就可以将这个lambda表达式用方法引用的方式表示，
         * 但是如果这个lambda表达式的比较复杂就不能用方法引用进行替换。实际上方法引用是lambda表达式的一种语法糖
         *
         * 注：引用的方法名与函数式接口的抽象方法可以不一样
         */
        FunctionInterface fun3 = new LambdaDemo()::doSomething;

        /* 方法引入等价于下面的写法 */
        FunctionInterface fun4 = new FunctionInterface() {
            @Override
            public int doSomething(String s) {
                return new LambdaDemo().doSomething(s);
            }
        };

        /* 执行方法引入的效果 */
        fun3.doSomething("fun3");
        fun4.doSomething("fun4");
    }

    public int doSomething(String x) {
        System.out.println("======我是方法引入执行的【" + x + "】======");
        return 9;
    }

}
