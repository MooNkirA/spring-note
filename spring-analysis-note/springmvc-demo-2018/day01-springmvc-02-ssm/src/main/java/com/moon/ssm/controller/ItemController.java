package com.moon.ssm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moon.ssm.entity.Item;
import com.moon.ssm.entity.QueryVo;
import com.moon.ssm.service.ItemService;

/**
 * 商品控制器
 * @author MoonZero
 */
// 使用注解交给spring管理
@Controller
public class ItemController {

	// 注入service层
	@Resource
	private ItemService itemService;

	/**
	 * 查询所有商品列表
	 */
	/*@RequestMapping("/queryItem.do")
	public ModelAndView queryItem() {
		// 1.创建ModelAndView对象
		ModelAndView mav = new ModelAndView();
	
		// 2.调用service层方法，查询所有商品
		List<Item> itemList = itemService.queryAllItems();
	
		// 3.设置响应商品的数据
		mav.addObject("itemList", itemList);
	
		// 4.设置响应商品列表页面
		mav.setViewName("item/itemList");
	
		// 5.返回模型视图对象
		return mav;
	}*/

	/**
	 * SpringMVC参数绑定：1.默认支持绑定的参数
	 * 需求：根据商品id查询商品数据
	 * 	http://127.0.0.1:8080/ssm/queryItemById.do?id=1
	 * 
	 * 	request形参，接收请求的商品id参数数据。
	 * 	springmvc在执行这个方法的时候，会把request对象传递过来
	 */
	/*@RequestMapping("/queryItemById.do")
	public ModelAndView queryItemById(HttpServletRequest request) {
		// 1.创建ModelAndView对象
		ModelAndView mav = new ModelAndView();
		
		// 2.通过request对象获取请求提交的参数
		String id = request.getParameter("id");
		
		// 3.调用service层方法，根据id查询的方法
		Item item = itemService.queryItemById(Integer.parseInt(id));
	
		// 4.设置响应商品的数据
		mav.addObject("item", item);
	
		// 5.设置响应商品列表页面
		mav.setViewName("item/itemEdit");
	
		// 6.返回模型视图对象
		return mav;
	}*/

	/**
	 * SpringMVC参数绑定：2.默认支持绑定的参数(使用Model绑定)
	 * 使用Model封装，可以直接返回String类型响应视图
	 * 
	 * 	request形参，接收请求的商品id参数数据。
	 * 	springmvc在执行这个方法的时候，会把request对象传递过来
	 */
	/*@RequestMapping("/queryItemById.do")
	public String queryItemById(Model model, HttpServletRequest request) {
		// 1.通过request对象获取请求提交的参数
		String id = request.getParameter("id");
	
		// 2.调用service层方法，根据id查询的方法
		Item item = itemService.queryItemById(Integer.parseInt(id));
	
		
		 *  3.使用Model对象设置响应商品的数据
		 *  addAttribute方法：响应模型数据
		 * 	 	attributeName参数：模型名称(响应)
		 *  	attributeValue参数：模型值（响应）
		 
		model.addAttribute("item", item);
	
		// 4.返回字符串，设置响应视图
		return "item/itemEdit";
	}*/

	/**
	 * SpringMVC参数绑定：3.简单类型参数绑定
	 * 	使用Model封装，可以直接返回String类型响应视图
	 * 	使用简单类型Integer，接收请求的商品id参数数据
	 */
	@RequestMapping("/queryItemById.do")
	public String queryItemById(Model model, Integer id) {
		// 1.调用service层方法，根据id查询的方法
		Item item = itemService.queryItemById(id);

		// 2.使用Model对象设置响应商品的数据
		model.addAttribute("item", item);

		// 3.返回字符串，设置响应视图
		return "item/itemEdit";
	}

	/**
	 * SpringMVC参数绑定：3.简单类型参数绑定(使用@RequestParam注解)
	 * 	使用Model封装，可以直接返回String类型响应视图
	 * 	使用简单类型Integer，接收请求的商品itemId参数数据
	 * 
	 * 	@RequestParam注解属性：
	 * 		value：设置请求的参数名称
	 * 		required：设置参数是否必须传递。取值true/false。true必须要传递；
	 * 	false可以传递，也可以不传递。默认是true。
	 * 		defaultValue：设置参数的默认值。如果传递，使用实际传递的参数值；如果不传递使用默认值
	 */
	/*@RequestMapping("/queryItemById.do")
	public String queryItemById(Model model,
			@RequestParam(value = "itemId", required = false, defaultValue = "3") Integer id) {
		// 1.调用service层方法，根据id查询的方法
		Item item = itemService.queryItemById(id);
	
		// 2.使用Model对象设置响应商品的数据
		model.addAttribute("item", item);
	
		// 3.返回字符串，设置响应视图
		return "item/itemEdit";
	}*/

	/**
	 * SpringMVC参数绑定：4.对象类型参数绑定
	 * 	使用Model封装，可以直接返回String类型响应视图
	 * 	使用Item对象，接收请求的商品参数数据
	 */
	@RequestMapping("/updateItem.do")
	public String updateItem(Model model, Item item) {

		try {
			// 1.调用service层方法，保存商品数据
			itemService.updateItem(item);
		} catch (Exception e) {
			e.printStackTrace();
			// 保存失败发生异常，设置响应视图，跳转到异常页面
			return "common/failure";
		}

		// 2.保存成功返回字符串，设置响应视图
		return "common/success";
	}

	/**
	 * pojo包装类型接收参数
	 * 	形参queryVo，接收请求的综合查询条件
	 */
	@RequestMapping("/queryItem.do")
	public String queryItem(Model model, QueryVo queryVo) {
	
		// 解决get请求中文乱码方式1：解码再编码
		/*try {
			if (queryVo != null && queryVo.getItem() != null) {
				// 1.获取参数数据
				String name = queryVo.getItem().getName();
	
				// 2.将数据按ISO-8859-1获取字节数组
				byte[] bytes = name.getBytes("ISO-8859-1");
	
				// 3.将字节数组使用UTF-8重新编码
				name = new String(bytes, "UTF-8");
	
				// 4.将编码后的数据重新设置到对象中
				queryVo.getItem().setName(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		// =========解决end==========
	
		// 1.测试包装类是否有接收数据
		if (queryVo != null && queryVo.getItem() != null) {
			System.out.println("页面提交的参数是：" + queryVo.getItem().getName());
		}
	
		// 2.调用service层方法，查询所有商品
		List<Item> itemList = itemService.queryAllItems();
	
		// 3.设置响应商品的数据
		model.addAttribute("itemList", itemList);
	
		// 4.返回字符串,设置响应视图
		return "item/itemList";
	}

}
