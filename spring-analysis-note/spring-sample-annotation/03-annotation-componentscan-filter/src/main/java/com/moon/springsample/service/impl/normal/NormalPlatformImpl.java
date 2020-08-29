package com.moon.springsample.service.impl.normal;

import com.moon.springsample.annotations.Scene;
import com.moon.springsample.service.PlatformService;
import org.springframework.stereotype.Service;

/**
 * 正常营业场景，运营平台业务实现类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-27 23:43
 * @description
 */
@Service("platformService")
@Scene("normal") // 使用自定义注解，标识当前是哪种场景的实现
public class NormalPlatformImpl implements PlatformService {

    @Override
    public void calcSalePercentage(String userType) {
        if ("member".equalsIgnoreCase(userType)) {
            System.out.println("正常营业，会员用户下单平台提成9%");
        } else if ("normal".equalsIgnoreCase(userType)) {
            System.out.println("正常营业，普通用户下单平台提成2%");
        }
    }

}
