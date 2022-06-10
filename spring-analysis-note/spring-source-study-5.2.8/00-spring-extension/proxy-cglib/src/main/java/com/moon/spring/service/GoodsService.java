package com.moon.spring.service;

/**
 * 测试接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-19 22:00
 * @description
 */
public interface GoodsService {

    String queryGoods(String param);

    String addGoods(String param);

    String editGoods(String param);

    String deleteGoods(String param);

    String fixedValue(String param);

}
