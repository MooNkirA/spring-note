package com.moon.springmvc.test;

import com.moon.springmvc.beans.BeanNoSetter;
import com.moon.springmvc.beans.NormalBean;
import com.moon.springmvc.beans.User;
import com.moon.springmvc.controller.InitBinderController;
import com.moon.springmvc.formatter.MyDateFormatter;
import org.junit.Test;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ServletRequestDataBinderFactory;

import java.lang.reflect.Type;
import java.util.Arrays;
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
        pvs.add("c", "2022/03/04");
        // 绑定属性
        dataBinder.bind(pvs);
        System.out.println(target);
    }

    // ServletDataBinder 类实现类型转换与数据绑定测试
    @Test
    public void testServletDataBinder() {
        NormalBean target = new NormalBean();
        // web 环境下数据绑定
        ServletRequestDataBinder dataBinder = new ServletRequestDataBinder(target);
        // 模拟请求对象
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("a", "10");
        request.setParameter("b", "hello");
        request.setParameter("c", "2022/03/04");
        // 将请求数据封装 java 对象中
        dataBinder.bind(new ServletRequestParameterPropertyValues(request));
        System.out.println(target);
    }

    // ServletDataBinder 对象特殊格式数据绑定测试
    @Test
    public void testServletDataBinderBySpecialCharacters() {
        // 创建请求对象
        MockHttpServletRequest request = new MockHttpServletRequest();
        // 定义特殊的日期格式
        request.setParameter("birthday", "2022|01|02");
        // 定义对象形式的字符串
        request.setParameter("address.name", "广州");
        User target = new User();
        // web 环境下数据绑定
        ServletRequestDataBinder dataBinder = new ServletRequestDataBinder(target);
        // 将请求数据封装 java 对象中
        dataBinder.bind(new ServletRequestParameterPropertyValues(request));
        // 对于特殊的日期格式是无法实现转换与绑定
        System.out.println(target);
    }

    // ServletRequestDataBinderFactory 类创建数据绑定器测试
    @Test
    public void testServletRequestDataBinderFactory() throws Exception {
        // 创建请求对象
        MockHttpServletRequest request = initRequest();
        User target = new User();

        // 创建 ServletRequestDataBinderFactory 无扩展的转换功能
        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(null, null);
        /*
         * 通过数据绑定工厂创建数据绑定器
         * WebDataBinder createBinder(NativeWebRequest webRequest, @Nullable Object target, String objectName)
         *  webRequest 参数：请求对象
         *  target 参数：待绑定的对象
         *  objectName 参数：对象的名称
         * 方法返回 WebDataBinder 对象 ServletRequestDataBinder 的父类
         */
        WebDataBinder dataBinder = factory.createBinder(new ServletWebRequest(request), target, "user");
        // 将请求数据封装 java 对象中
        dataBinder.bind(new ServletRequestParameterPropertyValues(request));
        // 此时没有做任何扩展，dataBinder 还是无法对特殊的日期格式实现转换与绑定
        System.out.println(target);
    }

    // 测试使用 @InitBinder 方式实现数据转换与绑定
    @Test
    public void testRequestDataBinderByInitBinder() throws Exception {
        // 创建请求对象
        MockHttpServletRequest request = initRequest();
        User target = new User();
        // 指定反射调用的哪个控制类中的那个方法
        InvocableHandlerMethod method = new InvocableHandlerMethod(new InitBinderController(), InitBinderController.class.getMethod("initBinder", WebDataBinder.class));
        // 创建 ServletRequestDataBinderFactory，指定回调标识了 @InitBinder 的方法
        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(Arrays.asList(method), null);
        // 通过数据绑定工厂创建数据绑定器
        WebDataBinder dataBinder = factory.createBinder(new ServletWebRequest(request), target, "user");
        // 将请求数据封装 java 对象中
        dataBinder.bind(new ServletRequestParameterPropertyValues(request));
        // 对特殊的日期格式实现转换与绑定
        System.out.println(target);
    }

    // 测试使用 ConversionService 方式实现数据转换与绑定
    @Test
    public void testRequestDataBinderByConversionService() throws Exception {
        // 创建请求对象
        MockHttpServletRequest request = initRequest();
        User target = new User();

        // 创建 ConversionService 对象
        FormattingConversionService service = new FormattingConversionService();
        // 增加自定义转换器
       service.addFormatter(new MyDateFormatter("用 ConversionService 方式扩展转换功能"));
        // WebBindingInitializer 实现类
        ConfigurableWebBindingInitializer webBindingInitializer = new ConfigurableWebBindingInitializer();
        // 增加自定义 ConversionService 对象
        webBindingInitializer.setConversionService(service);

        // 创建 ServletRequestDataBinderFactory
        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(null, webBindingInitializer);
        // 通过数据绑定工厂创建数据绑定器
        WebDataBinder dataBinder = factory.createBinder(new ServletWebRequest(request), target, "user");
        // 将请求数据封装 java 对象中
        dataBinder.bind(new ServletRequestParameterPropertyValues(request));
        // 对特殊的日期格式实现转换与绑定
        System.out.println(target);
    }

    // 测试同时使用 @InitBinder 方式 与 ConversionService 方式实现数据转换与绑定
    @Test
    public void testRequestDataBinderByInitBinderAndConversionService() throws Exception {
        // 创建请求对象
        MockHttpServletRequest request = initRequest();
        User target = new User();

        // 指定反射调用的哪个控制类中的那个方法
        InvocableHandlerMethod method = new InvocableHandlerMethod(new InitBinderController(), InitBinderController.class.getMethod("initBinder", WebDataBinder.class));
        // 创建 ConversionService 对象
        FormattingConversionService service = new FormattingConversionService();
        // 增加自定义转换器
       service.addFormatter(new MyDateFormatter("用 ConversionService 方式扩展转换功能"));
        // WebBindingInitializer 实现类
        ConfigurableWebBindingInitializer webBindingInitializer = new ConfigurableWebBindingInitializer();
        // 增加自定义 ConversionService 对象
        webBindingInitializer.setConversionService(service);

        // 创建 ServletRequestDataBinderFactory，同时指定 @InitBinder 与 ConversionService 来实现数据转换
        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(Arrays.asList(method), webBindingInitializer);
        // 通过数据绑定工厂创建数据绑定器
        WebDataBinder dataBinder = factory.createBinder(new ServletWebRequest(request), target, "user");
        // 将请求数据封装 java 对象中
        dataBinder.bind(new ServletRequestParameterPropertyValues(request));
        // 对特殊的日期格式实现转换与绑定
        System.out.println(target);
    }

    // 测试使用默认 ConversionService 实现数据转换与绑定
    @Test
    public void testRequestDataBinderByDefaultConversionService() throws Exception {
        // 创建请求对象
        MockHttpServletRequest request = initRequest();
        User target = new User();

        /*
         * 创建默认的 ConversionService 实现 DefaultFormattingConversionService
         * 如果是 Spring boot 工程，也可以使用 org.springframework.boot.convert.ApplicationConversionService 默认实现
         */
        DefaultFormattingConversionService service = new DefaultFormattingConversionService();
        // WebBindingInitializer 实现类
        ConfigurableWebBindingInitializer webBindingInitializer = new ConfigurableWebBindingInitializer();
        // 增加默认的 ConversionService 实现
        webBindingInitializer.setConversionService(service);

        // 创建 ServletRequestDataBinderFactory
        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(null, webBindingInitializer);
        // 通过数据绑定工厂创建数据绑定器
        WebDataBinder dataBinder = factory.createBinder(new ServletWebRequest(request), target, "user");
        // 将请求数据封装 java 对象中
        dataBinder.bind(new ServletRequestParameterPropertyValues(request));
        // 对特殊的日期格式实现转换与绑定
        System.out.println(target);
        Type superclass = User.class.getGenericSuperclass();
    }

    private MockHttpServletRequest initRequest() {
        // 创建请求对象
        MockHttpServletRequest request = new MockHttpServletRequest();
        // 定义特殊的日期格式
        request.setParameter("birthday", "2022|01|02");
        // 定义对象形式的字符串
        request.setParameter("address.name", "广州");
        return request;
    }

}
