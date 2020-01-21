package com.moon.spring.bean;

public abstract class ShowSixClass {

    public void showsix() {
        // 配置了lookup-method子标签后，当调用getPeople()方法时，返回值People对象实际为配置bean值的相应对象
        getPeople().showsix();
    }

    // @Lookup("woman")
    // 注意此方法不一定是抽象的
    public abstract People getPeople();

}
