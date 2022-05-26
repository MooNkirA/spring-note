package com.moon.ssm.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 自定义参数转换器
 * @author MoonZero
 * 
 * Converter<S, T>接口
 * 	S：Source：源，转换前的数据，这里是字符串类型（String）的商品生产日期
 * 	T:Target：目标,转换后的数据，这里是Date类型的商品生产日期
 */
public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		try {
			// 定义日期格式化对象，指定日期的格式:2016-02-03 13:22:53
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 返回格式后的日期对象
			return format.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 转换不成功返回null;
		return null;
	}
}
