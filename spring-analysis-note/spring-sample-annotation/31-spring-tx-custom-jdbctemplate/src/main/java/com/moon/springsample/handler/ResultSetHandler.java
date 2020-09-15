package com.moon.springsample.handler;

import java.sql.ResultSet;

/**
 * 结果集的处理器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-15 10:11
 * @description
 */
public interface ResultSetHandler<T> {

    /**
     * 处理结果集方法
     *
     * @param resultSet
     * @return
     */
    Object handler(ResultSet resultSet);

}
