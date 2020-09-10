package com.moon.springsample.extension.impl;

import com.moon.springsample.domain.User;
import com.moon.springsample.extension.ValidateExtensionService;

/**
 * 用户业务校验扩展实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-10 14:28
 * @description
 */
public class ValidateExtensionServiceImpl implements ValidateExtensionService {

    @Override
    public boolean checkUser(User user) {
        if (user.getNickname() == null) {
            return true;
        }
        // 不为空，则校验是否包含过滤的字眼
        return !user.getNickname().contains("孙子");
    }

}
