package com.moon.springmvc.test;

import com.moon.springmvc.beans.BeanNoSetter;
import com.moon.springmvc.beans.NormalBean;
import org.junit.Test;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;

import java.util.Date;

/**
 * Spring 各种数据类型转换实现类测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-01 16:01
 * @description
 */
public class TypeConversionTest {

    // SimpleTypeConverter 类实现类型转换测试
    @Test
    public void testSimpleConverter() {
        // 仅只有类型转换的功能
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();
        Integer number = typeConverter.convertIfNecessary("13", int.class);
        Date date = typeConverter.convertIfNecessary("2022/03/04", Date.class);
        System.out.println(number);
        System.out.println(date);
    }

    // BeanWrapperImpl 类实现类型转换与数据绑定测试
    @Test
    public void testBeanWrapperImpl() {
        // 利用反射原理，通过 setter 方法为 bean 的属性赋值
        NormalBean target = new NormalBean();
        BeanWrapperImpl wrapper = new BeanWrapperImpl(target);
        wrapper.setPropertyValue("a", "10");
        wrapper.setPropertyValue("b", "hello");
        wrapper.setPropertyValue("c", "2022/03/04");
        System.out.println(target);
    }

    // DirectFieldAccessor 类实现类型转换与数据绑定测试
    @Test
    public void testDirectFieldAccessor() {
        // 利用反射原理，直接设置 Field 值（无需提供 setter 方法）
        BeanNoSetter target = new BeanNoSetter();
        DirectFieldAccessor accessor = new DirectFieldAccessor(target);
        accessor.setPropertyValue("a", "10");
        accessor.setPropertyValue("b", "hello");
        accessor.setPropertyValue("c", "2022/03/04");
        System.out.println(target);
    }

    // DataBinder 类实现类型转换与数据绑定测试
    @Test
    public void testDataBinder() {
        // 利用反射原理，直接设置 Field 值（无需提供 setter 方法）
        BeanNoSetter target = new BeanNoSetter();
        DataBinder dataBinder = new DataBinder(target);
        /*
         * 设置 directFieldAccess 属性，用于判断反射时选择 Property 方式还是 Field 方式赋值
         * 因为 bean 无 setter 方法，设置 directFieldAccess 为 true
         */
        dataBinder.initDirectFieldAccess();
        // 设置属性与值
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add("a", "10");
        pvs.add("b", "hello");
        pvs.add("c", "1999/03/04");
        // 绑定属性
        dataBinder.bind(pvs);
        System.out.println(target);
    }

    // ServletDataBinder 类实现类型转换与数据绑定测试
    @Test
    public void testServletDataBinder() {
        // web 环境下数据绑定
        NormalBean target = new NormalBean();
        ServletRequestDataBinder dataBinder = new ServletRequestDataBinder(target);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("a", "10");
        request.setParameter("b", "hello");
        request.setParameter("c", "1999/03/04");

        dataBinder.bind(new ServletRequestParameterPropertyValues(request));

        System.out.println(target);
    }

    // ServletDataBinderFactory 类实现类型转换与数据绑定测试
    @Test
    public void testServletDataBinderFactory() {
        // see springmvc-sample project
    }

}
