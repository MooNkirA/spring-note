package com.moon.springsample.service.impl.anniversary;

import com.moon.springsample.annotations.Scene;
import com.moon.springsample.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * 周年欢活动场景，订单业务实现类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-27 23:31
 * @description
 */
@Service("orderService")
@Scene("anniversary") // 使用自定义注解，标识当前是哪种场景的实现
public class AnniversaryOrderServiceImpl implements OrderService {

    @Override
    public void calcOrderDiscount(String userType) {
        // 判断用户类型
        if ("member".equalsIgnoreCase(userType)) {
            System.out.println("周年庆活动，会员用户下单优惠50元");
        } else if ("normal".equalsIgnoreCase(userType)) {
            System.out.println("周年庆活动，普通用户下单优惠10元");
        }
    }

}
