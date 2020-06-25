package com.moon.crm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moon.crm.po.BaseDict;
import com.moon.crm.po.Customer;
import com.moon.crm.po.QueryVo;
import com.moon.crm.service.BaseDictService;
import com.moon.crm.service.CustomerService;
import com.moon.crm.utils.Page;

/**
 * 客户模块控制器
 * @author MoonZero
 */
// 使用注解让spring容器管理
@Controller
// 使用映射注解进行分类管理
@RequestMapping("cust")
public class CustomerController {

	// 注入字典模块业务层接口
	@Autowired
	private BaseDictService baseService;

	// 注入客户模块业务层接口
	@Autowired
	private CustomerService customerService;

	/**
	 * 查询客户列表显示
	 * 	访问url: 127.0.0.1:8080/crm/cust/list.do
	 */
	@RequestMapping("list")
	public String list(Model model, QueryVo queryVo) {

		// 1.查询客户来源(002)\所属行业(001)\客户级别数据(006)
		List<BaseDict> fromType = baseService.queryBaseDictByTypeCode("002");
		List<BaseDict> industryType = baseService.queryBaseDictByTypeCode("001");
		List<BaseDict> levelType = baseService.queryBaseDictByTypeCode("006");

		// 2.设置响应的模型的数据
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);

		// 3.调用客户模块业务层方法，查询客户分页数据对象
		Page<Customer> page = customerService.queryCustomerByPage(queryVo);
		// 4.设置响应的模型的分页数据（模型名称与页面定义一致）
		model.addAttribute("page", page);

		// 5.设置提交的条件查询数据回显，将包装类中的数据设置到模型中(模型名称与页面定义一致)
		model.addAttribute("custName", queryVo.getCustName());
		model.addAttribute("custSource", queryVo.getCustSource());
		model.addAttribute("custIndustry", queryVo.getCustIndustry());
		model.addAttribute("custLevel", queryVo.getCustLevel());

		// 返回视图
		return "customer";
	}

	/**
	 * 修改客户-回显用户数据
	 * 	访问url:"<%=basePath%>cust/edit"
	 * 	查看页面是发送ajax请求，需要返回json格式的客户对象
	 */
	@RequestMapping("edit")
	// 设置返回json格式对象
	@ResponseBody
	public Customer edit(Long id) {
		// 调用业务查询方法，并将查询结果返回
		return customerService.queryCustomerById(id);
	}

	/**
	 * 修改客户-保存用户数据
	 * 	访问url："<%=basePath%>cust/update"
	 */
	@RequestMapping("update")
	// 设置返回json格式，主要不是返回数据，是返回【200】成功状态码
	@ResponseBody
	public void update(Customer customer) {
		// 调用业务层保存数据的方法
		customerService.updateCustomerById(customer);
	}

	/**
	 * 删除客户
	 * 	访问url:"<%=basePath%>cust/delcust"
	 */
	/*@RequestMapping("delcust")
	// 设置返回json格式，主要不是返回数据，是返回【200】成功状态码
	@ResponseBody
	public void delete(Long id) {
		// 调用业务层删除数据的方法
		customerService.deleteCustomerById(id);
	}*/

	/**
	 * 删除客户方式2
	 * 	访问url:"<%=basePath%>cust/delcust"
	 */
	@RequestMapping("delcust")
	// 设置返回json格式的map集合
	@ResponseBody
	public Map<String, String> delete(Long id) {
		// 1.创建用于封装结果的map集合
		Map<String, String> result = new HashMap<String, String>();
	
		try {
			// 模拟异常
			// int i = 1 / 0;
			// 2.调用业务层删除数据的方法
			customerService.deleteCustomerById(id);
			// 3.设置返回结果map集合
			result.put("flag", "success");
		} catch (Exception e) {
			e.printStackTrace();
			// 如果删除失败，设置返回结果
			result.put("flag", "failure");
		}
	
		// 返回结果
		return result;
	}
}
