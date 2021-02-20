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
        //
        System.out.println(goodsService.fixedValue("MooN"));
    }

}
