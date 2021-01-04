package com.moon.spring.service;

import com.moon.spring.bean.People;

/**
 * 用于测试 <bean> 子标签 <lookup-method> 使用
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-3 18:16
 * @description
 */
public abstract class PeopleService {

    public void showGender() {
        // 配置了lookup-method子标签后，当调用getPeople()方法时，返回值People对象实际为配置bean值的相应对象
        getPeople().showGender();
    }

    // @Lookup("woman")
    // 注意此方法不一定是抽象的
    public abstract People getPeople();

}
