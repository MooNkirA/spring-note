package com.moon.ssm.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.moon.ssm.entity.Item;
import com.moon.ssm.entity.QueryVo;
import com.moon.ssm.exception.SsmException;
import com.moon.ssm.service.ItemService;

/**
 * 商品控制器
 * @author MoonZero
 */
// 使用注解交给spring管理
@Controller
// @RequestMapping注解，在类上面使用，可以对请求的url进行分类管理（窄化请求）
// @RequestMapping("item")
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
	 * @throws SsmException 
	 */
	@RequestMapping("/queryItemById.do")
	public String queryItemById(Model model, Integer id) throws SsmException {
		// 1.调用service层方法，根据id查询的方法
		Item item = itemService.queryItemById(id);

		// ===========增加异常测试==============

		// 判断是否查询到商品
		if (item == null) {
			throw new SsmException("商品不存在！！");
		}

		// 模拟运行时异常
		// int i = 1 / 0;

		// ===========异常测试结束==============

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
	 * 	直接返回String类型响应视图
	 * 	使用Item对象，接收请求的商品参数数据
	 * 文件上传：
	 * 	<input type="file"  name="pictureFile"/>
	 * 形参名称与页面一致，使用MultipartFile对象接收上传的图片
	 */
	@RequestMapping("/updateItem.do")
	public String updateItem(Item item, MultipartFile pictureFile) {

		// =============文件上传start==================
		// 1.先判断接收上传文件的对象是否为空
		if (pictureFile != null && pictureFile.getOriginalFilename() != null) {
			// 2.获取上传文件的名称
			String fileName = pictureFile.getOriginalFilename();
			// 获取上传文件的后缀名(根据最后.的索引来截取)
			String suffixName = fileName.substring(fileName.lastIndexOf("."));

			// 3.重新命名文件(包括后缀)
			String newFileName = System.currentTimeMillis() + suffixName;

			try {
				// 4.创建输出目标文件对象，使用框架方法保存文件到本地
				File dest = new File("E:\\Downloads\\" + newFileName);
				pictureFile.transferTo(dest);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 5.保存文件后，将文件的名称保存商品对象中
			item.setPic(newFileName);
		}
		// =============文件上传end==================

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
	/*@RequestMapping("/queryItem.do")
	public String queryItem(Model model, QueryVo queryVo) {
	
		// 解决get请求中文乱码方式1：解码再编码
		try {
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
		}
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
	}*/

	/**
	 * 数组类型绑定(直接定义数组做为形参)
	 * 	形参ids，用于接收请求的多个商品id
	 */
	/*@RequestMapping("/queryItem.do")
	public String queryItem(Model model, Integer[] ids) {
	
		// 1.测试数组类型绑定
		if (ids != null && ids.length > 0) {
			for (Integer i : ids) {
				System.out.println("商品的ID：" + i);
			}
		}
	
		// 2.调用service层方法，查询所有商品
		List<Item> itemList = itemService.queryAllItems();
	
		// 3.设置响应商品的数据
		model.addAttribute("itemList", itemList);
	
		// 4.返回字符串,设置响应视图
		return "item/itemList";
	}*/

	/**
	 * 数组类型绑定(形参使用包装类)
	 */
	/*@RequestMapping(value= {"/queryItem.do"})
	public String queryItem(Model model, QueryVo queryVo) {
	
		// 1.测试包装类的数组类型绑定
		if (queryVo != null && queryVo.getIds() != null) {
			for (Integer i : queryVo.getIds()) {
				System.out.println("商品的ID：" + i);
			}
		}
	
		// 2.调用service层方法，查询所有商品
		List<Item> itemList = itemService.queryAllItems();
	
		// 3.设置响应商品的数据
		model.addAttribute("itemList", itemList);
	
		// 4.返回字符串,设置响应视图
		return "item/itemList";
	}*/

	/**
	 * List集合类型绑定
	 */
	/*@RequestMapping("/queryItem.do")
	public String queryItem(Model model, QueryVo queryVo) {
	
		// 1.测试包装类的数组类型绑定
		if (queryVo != null && queryVo.getItemList() != null && queryVo.getItemList().size() > 0) {
			for (Item i : queryVo.getItemList()) {
				System.out.println(i);
			}
		}
	
		// 2.调用service层方法，查询所有商品
		List<Item> itemList = itemService.queryAllItems();
	
		// 3.设置响应商品的数据
		model.addAttribute("itemList", itemList);
	
		// 4.返回字符串,设置响应视图
		return "item/itemList";
	}*/

	/**
	 * 高级参数绑定map（了解）
	 * 	跳转登录页面
	 * 		http://127.0.0.1:8080/ssm/toLogin.do?id=1&name=anan
	 *
	 *  注意事项：使用map实现参数绑定，必须要配合@RequestParam注解一起使用
	 */
	@RequestMapping("/toLogin.do")
	public String toLogin(@RequestParam Map<String, String> map) {
		System.out.println("id=" + map.get("id"));
		System.out.println("name=" + map.get("name"));
		return "common/success";
	}

	/**
	 * @RequestMapping注解测试专用
	 * 
	 * 	属性：
	 * 		value：是一个数组，可以配置多个请求的url
	 * 				细节："/"和".do"可以省略
	 * 
	 * 		method:限制http请求方法
	 * 			细节：
	 * 				RequestMethod.POST:只允许post请求
	 * 				RequestMethod.POST,RequestMethod.GET：只允许post/get请求
	 */
	// @RequestMapping(value = "queryItem.do")
	// @RequestMapping(value = { "queryItem.do", "queryItem88.do" })
	@RequestMapping(value = "queryItem", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView queryItem(QueryVo queryVo, Integer[] ids) {
		// 1.创建ModelAndView对象
		ModelAndView mav = new ModelAndView();
		// 2.响应商品数据
		// 查询数据库中的商品数据，替换静态的商品数据
		List<Item> itemList = this.itemService.queryAllItems();
		mav.addObject("itemList", itemList);
		// 3.响应商品列表页面
		mav.setViewName("item/itemList");
		return mav;
	}

	/**
	 * controller方法返回void测试专用
	 * 	访问url：http://127.0.0.1:8080/ssm/returnVoid.do
	 */
	@RequestMapping("/returnVoid.do")
	public void controllerReturnVoid(HttpServletRequest request, HttpServletResponse response) {

		/*try {
			// 1.request转发测试
			request.getRequestDispatcher("queryItem.do").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		/*try {
			// 2.response重定向测试
			response.sendRedirect("queryItem.do");
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		try {
			// 3.response响应数据
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write("使用response直接输出json数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * controller方法返回String测试专用
	 * 	访问url：http://127.0.0.1:8080/ssm/returnString.do
	 */
	@RequestMapping("returnString")
	public String controllerReturnString() {

		// 1.测试forward转发
		// return "forward:queryItem.do";

		// 2.测试redirect重定向
		return "redirect:queryItem.do";
	}

	/**
	 * json数据交互测试专用
	 * 需求：
	 * 	请求商品json格式的数据，转换成java对象
	 * 	响应一个java对象，转换成json格式的数据
	 */
	@RequestMapping("testJson.do")
	// 使用注解配置返回对象转成json格式数据
	@ResponseBody
	// 使用注解配置请求json数据转成对象
	public Item testJson(@RequestBody Item item) {
		return item;
	}

	/**
	 * restful风格测试专用
	 * 		使用restful风格，实现根据商品id查询商品数据。
	 * 		http://127.0.0.1:8080/ssm/item/1 
	 * 
	 * 	{id}：路径变量（模版参数）
	 * 	@PathVariable注解：把路径变量的值，绑定到方法的形参上
	 * 注解写法：
	 * 		@PathVariable(name="id")
	 * 		@PathVariable(value="id")
	 * 		@PathVariable("id")
	 * 
	 * 前提是路径变量的名称，与方法的形参名称一致：
	 * 		@PathVariable() 或者 @PathVariable
	 */
	@RequestMapping("/item/{id}")
	@ResponseBody
	public Item testRestful(@PathVariable Integer id) {
		// 1.调用业务层方法，根据id查询
		Item item = itemService.queryItemById(id);
		// 2.返回查询结果
		return item;
	}

	/*// 模拟restful风格执行新增方法
	@RequestMapping(value = "/item", method = RequestMethod.POST)
	public String testRestfulInsert(Item item) {
		System.out.println("执行了新增方法");
		return "common/success";
	}
	// 模拟restful风格执行更新方法
	@RequestMapping(value = "/item", method = RequestMethod.PUT)
	public String testRestfulUpdate(Item item) {
		System.out.println("执行了修改方法");
		return "common/success";
	}
	// 模拟restful风格执行查询方法
	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
	public String testRestfulQuery(@PathVariable Integer id) {
		System.out.println("执行了查询方法");
		return "common/success";
	}
	// 模拟restful风格执行删除方法
	@RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
	public String testRestfulDelete(@PathVariable Integer id) {
		System.out.println("执行了删除方法");
		return "common/success";
	}*/
	
	/**
	 * 拦截器测试专用
	 * 	访问url：http://127.0.0.1:8080/ssm/interceptor.do
	 */
	@RequestMapping("/interceptor.do")
	public String testInterceptor(Model model){
		
		System.out.println("测试拦截器--testInterceptor方法执行中......");
		
		model.addAttribute("message1", "我是testInterceptor方法......");
		
		return "inter/interceptor";
	}
}
