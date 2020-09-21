package com.moon.springmvc.web.controller;

import com.moon.springmvc.domain.User;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注解使用示例控制器 - @CrossOrigin注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-21 09:00
 * @description
 */
@Controller
public class CrossOriginController {
    /*
     * 跨域访问的定义：
     *   当一个域名请求另一个域名的资源时，即是跨域
     *   怎么是不同的域？协议，主机，端口任何一个元素不相同都为不同的域，请求访问即为跨域
     * 本地测试模拟域名：
     *   查找C:/Windows/System32/drivers/ect/hosts（文件）
     *   修改此文件，建立IP和域名的对应关系
     * 刷新域名不重启计算机的命令，打开cmd命令行输入：：
     *   ipconfig /displaydns
     *   ipconfig /flushdns
     */

    // ****************************************
    //           以下为示例代码
    // ****************************************
    /*
     * 定义跨域请求访问的测试方法
     */
    @RequestMapping("/useCrossOrigin")
    @ResponseBody
    /*
     * @CrossOrigin注解用于指定是否支持跨域访问
     *      修饰方法：代表此方法支持跨域访问
     *      修饰类：代表此类中所有方法支持跨域访问
     */
    @CrossOrigin
    public String useCrossOrigin(@RequestBody(required = false) User user) {
        return "user is " + user;
    }

    /**
     * 请求获取图片流
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/getImage")
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. 获取ServletContext对象
        ServletContext servletContext = request.getServletContext();
        // 2. 获取图片的磁盘绝对路径
        String path = servletContext.getRealPath("/images/6.jpg");
        // 3. 获取spring提供的文件系统资源对象
        Resource resource = new FileSystemResource(path);
        // 4. 使用spring的工具类把resource中的文件转换成一个字节数组
        byte[] images = FileCopyUtils.copyToByteArray(resource.getFile());
        // 5. 使用response设置响应消息头
        response.setContentType("application/octet-stream");
        // 6. 输出字节数组
        response.getOutputStream().write(images, 0, images.length);
    }

    /**
     * 请求获取css文件流
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/getCss")
    public void getCss(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. 获取ServletContext对象
        ServletContext servletContext = request.getServletContext();
        // 2. 获取图片的磁盘绝对路径
        String path = servletContext.getRealPath("/css/demo.css");
        // 3. 获取spring提供的文件系统资源对象
        Resource resource = new FileSystemResource(path);
        // 4. 使用spring的工具类把resource中的文件转换成一个字节数组
        byte[] images = FileCopyUtils.copyToByteArray(resource.getFile());
        // 5. 使用response设置响应消息头
        response.setContentType("text/css");
        // 6. 输出字节数组
        response.getOutputStream().write(images, 0, images.length);
    }

}
