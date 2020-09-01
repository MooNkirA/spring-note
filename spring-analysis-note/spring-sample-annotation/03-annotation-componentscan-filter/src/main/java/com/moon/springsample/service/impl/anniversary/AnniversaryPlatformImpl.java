package com.moon.springsample.service.impl.anniversary;

import com.moon.springsample.annotations.Scene;
import com.moon.springsample.service.PlatformService;
import org.springframework.stereotype.Service;

/**
 * 周年欢场景 运营平台业务实现类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-27 23:37
 * @description
 */
@Service("platformService")
@Scene("anniversary") // 使用自定义注解，标识当前是哪种场景的实现
public class AnniversaryPlatformImpl implements PlatformService {

    @Override
    public void calcSalePercentage(String userType) {
        if ("member".equalsIgnoreCase(userType)) {
            System.out.println("周年庆活动，会员用户下单平台提成15%");
        } else if ("normal".equalsIgnoreCase(userType)) {
            System.out.println("周年庆活动，普通用户下单平台提成8%");
        }
    }

}
