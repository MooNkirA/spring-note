package com.moon.spring.designPattern.entrust;

public class Boss implements Company {

    Sun sun = new Sun();

    /*
     * 此处直接委托给Sun类去实现，此处就不需要编写任何关于product方法的业务逻辑
     * 即交给Sun类去执行业务
     */
    @Override
    public void product() {
        sun.product();
    }

}
