package com.moon.springsample.extension;

import com.moon.springsample.domain.User;

/**
 * 用于扩展用户业务验证的接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-10 14:24
 * @description
 */
public interface ValidateExtensionService {

    /**
     * 校验用户信息
     *
     * @param user
     * @return
     */
    boolean checkUser(User user);

}
