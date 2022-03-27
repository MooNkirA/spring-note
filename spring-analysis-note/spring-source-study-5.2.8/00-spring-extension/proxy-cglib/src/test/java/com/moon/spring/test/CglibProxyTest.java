package com.moon.spring.test;

import com.moon.spring.proxy.CglibBeanFactory;
import com.moon.spring.service.GoodsService;
import org.junit.Test;

/**
 * CGlib 代理测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-19 22:43
 * @description
 */
public class CglibProxyTest {

    @Test
    public void testCglibBasic() {
        // 获取代理
        GoodsService goodsService = (GoodsService) CglibBeanFactory.getInstance();
        // 调用相应的方法
        System.out.println(goodsService.queryGoods("MooN"));
        System.out.println(goodsService.addGoods("iPhone18"));
        System.out.println(goodsService.editGoods("战神游戏本"));
        System.out.println(goodsService.deleteGoods("机械键盘"));
        System.out.println(goodsService.fixedValue("kirA"));
    }

}
