package com.moon.springmvc.web.controller;

import com.moon.springmvc.domain.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注解使用示例控制器 - @PathVariable注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-20 17:03
 * @description
 */
// @Controller
// @ResponseBody
@RestController // 等同于上面两个注解
@RequestMapping("user")
public class PathVariableController {

    /*
     * rest风格url - 保存方法
     */
    @RequestMapping(method = RequestMethod.POST)
    public String save(@RequestBody User user) {
        return "user is " + user;
    }

    /*
     * rest风格url - 更新方法
     *  在@RequestMapping中使用“{xxx}”占位符
     *  @PathVariable注解获取映射占位符的值，
     *  如果方法形参的名称与占位符一致，则可以直接映射值
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public String update(@PathVariable Integer id, @RequestBody User user) {
        // 给user的id赋值
        user.setId(id);
        return "user is " + user;
    }

    /*
     * rest风格url - 删除方法
     * @PathVariable注解获取映射占位符的值，
     * 如果方法形参的名称与占位符不一致，则设置value与name属性值，指定url映射中占位符的名称
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public String delete(@PathVariable("userId") Integer id) {
        return "删除用户的Id是：" + id;
    }

    /*
     * rest风格url - 根据id查询方法
     * @PathVariable注解的required属性，用于指定是否必须有此占位符
     *  默认值为true，即没有映射占位符的值时会报错
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String findById(@PathVariable(value = "id", required = false) Integer id) {
        return "查询用户的Id是：" + id;
    }

}
