package com.moon.springmvc.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 自定义日期转换器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-06 16:17
 * @description
 */
public class MyDateFormatter implements Formatter<Date> {

    private final String desc; // 用于测试是哪种方式进行转换

    public MyDateFormatter(String desc) {
        this.desc = desc;
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        System.out.println(desc + "进入了 MyDateFormatter.parse 方法");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy|MM|dd");
        return sdf.parse(text);
    }

    @Override
    public String print(Date date, Locale locale) {
        System.out.println(desc + "进入了 MyDateFormatter.print 方法");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy|MM|dd");
        return sdf.format(date);
    }

}
