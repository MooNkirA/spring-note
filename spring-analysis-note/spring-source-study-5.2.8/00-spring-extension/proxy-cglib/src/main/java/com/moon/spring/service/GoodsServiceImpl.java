package com.moon.spring.service;

/**
 * 被代理类，CGlib与jdk动态不同，代理与被代理不需要实现同一接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-19 22:06
 * @description
 */
public class GoodsServiceImpl implements GoodsService {

    @Override
    public String queryGoods(String param) {
        System.out.println("=== queryGoods ===");
        return "queryGoods";
    }

    @Override
    public String addGoods(String param) {
        System.out.println("=== addGoods ===");
        return "addGoods";
    }

    @Override
    public String editGoods(String param) {
        System.out.println("=== editGoods ===");
        return "editGoods";
    }

    @Override
    public String deleteGoods(String param) {
        System.out.println("=== deleteGoods ===");
        return "deleteGoods";
    }

    @Override
    public String fixedValue(String param) {
        System.out.println("=== fixedValue ===");
        return "fixedValue";
    }
}
