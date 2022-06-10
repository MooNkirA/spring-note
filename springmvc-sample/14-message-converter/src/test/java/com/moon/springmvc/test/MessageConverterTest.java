package com.moon.springmvc.test;

import com.moon.springmvc.controller.DemoController;
import com.moon.springmvc.pojo.User;
import org.junit.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Spring 的 MessageConverter 实现测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-09 10:13
 * @description
 */
public class MessageConverterTest {

    // MappingJackson2HttpMessageConverter 模拟实现 http 响应对象转 json
    @Test
    public void test1() throws IOException {
        MockHttpOutputMessage message = new MockHttpOutputMessage();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        if (converter.canWrite(User.class, MediaType.APPLICATION_JSON)) {
            converter.write(new User("张三", 18), MediaType.APPLICATION_JSON, message);
            System.out.println(message.getBodyAsString());
        }
    }

    // MappingJackson2XmlHttpMessageConverter 模拟实现 http 响应对象转 xml
    @Test
    public void test2() throws IOException {
        MockHttpOutputMessage message = new MockHttpOutputMessage();
        MappingJackson2XmlHttpMessageConverter converter = new MappingJackson2XmlHttpMessageConverter();
        if (converter.canWrite(User.class, MediaType.APPLICATION_XML)) {
            converter.write(new User("李四", 20), MediaType.APPLICATION_XML, message);
            System.out.println(message.getBodyAsString());
        }
    }

    // MappingJackson2HttpMessageConverter 模拟实现 http 请求中的json，转成对象
    @Test
    public void test3() throws IOException {
        MockHttpInputMessage message = new MockHttpInputMessage("{\"name\":\"MooNkirA\",\"age\":20}".getBytes(StandardCharsets.UTF_8));
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        if (converter.canRead(User.class, MediaType.APPLICATION_JSON)) {
            Object read = converter.read(User.class, message);
            System.out.println(read);
        }
    }

    /*
      请求的 MediaType 的选择顺序如下：
        - 首先看控制方法的 @RequestMapping 上有没有指定
        - 其次看 request 的 Accept 头有没有指定
        - 最后按 MessageConverter 的顺序, 谁能谁先转换
     */
    @Test
    public void test4() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        ServletWebRequest webRequest = new ServletWebRequest(request, response);

        request.addHeader("Accept", "application/xml"); // 转换优先级其次
        response.setContentType("application/json"); // 转换优先级最高
        // 如果请求头与响应类型都没有，则按设置的顺序要决定使用哪个转换
        RequestResponseBodyMethodProcessor processor = new RequestResponseBodyMethodProcessor(
                Arrays.asList(new MappingJackson2HttpMessageConverter(), new MappingJackson2XmlHttpMessageConverter())
        );
        processor.handleReturnValue(
                new User("张三", 18),
                new MethodParameter(DemoController.class.getMethod("user"), -1),
                new ModelAndViewContainer(),
                webRequest
        );
        System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
    }

}
