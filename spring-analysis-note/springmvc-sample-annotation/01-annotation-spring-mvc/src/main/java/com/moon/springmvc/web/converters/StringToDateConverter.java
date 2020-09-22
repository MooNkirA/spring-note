package com.moon.springmvc.web.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 自定义类型转换器 - 处理字符串转日期类型
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-22 16:14
 * @description
 */
@Component
public class StringToDateConverter implements Converter<String, Date> {

    /*
     * Converter<S, T>接口的泛型
     *  S：转换处理方法形参的类型，即待转换的内容
     *  T：转换处理方法返回值的类型，即转换后返回的内容
     */
    @Override
    public Date convert(String source) {
        // 1. 判断来源是否有值
        if (StringUtils.isEmpty(source)) {
            throw new NullPointerException("Source can not be null!");
        }
        try {
            // 2. 定义转换器
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            // 3. 转换并返回
            return format.parse(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
