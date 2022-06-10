package com.moon.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moon.crm.mapper.CustomerMapper;
import com.moon.crm.po.Customer;
import com.moon.crm.po.QueryVo;
import com.moon.crm.service.CustomerService;
import com.moon.crm.utils.Page;

/**
 * 客户模块业务层实现类
 * @author MoonZero
 */
// 使用注解给spring容器管理
@Service
public class CustomerServiceImpl implements CustomerService {

	// 注入客户mapper接口代理
	@Autowired
	private CustomerMapper customerMapper;

	/**
	 * 1.多条件分页查询客户数据
	 */
	@Override
	public Page<Customer> queryCustomerByPage(QueryVo queryVo) {
		// 1.根据页面提交的参数计算当前开始索引
		// 开始索引的计算公式：（当前页-1）*每页显示的大小
		queryVo.setStart((queryVo.getPage() - 1) * queryVo.getRows());

		// 2.调用客户接口方法，根据多条件查询客户数据
		List<Customer> customerList = customerMapper.queryCustomerList(queryVo);

		// 3.调用客户接口方法，根据多条件查询客户总记录数
		Integer total = customerMapper.countCustomers(queryVo);

		/*
		 *  4.创建分页对象，传入从数据库查询出来的数据
		 * 
		 * @param total 查询数据总条数
		 * @param page 当前页码数
		 * @param size 每页显示数据条数
		 * @param rows 查询结果集
		 *   public Page(int total, int page, int size, List<T> rows)
		 */
		Page<Customer> page = new Page<Customer>(total, queryVo.getPage(), queryVo.getRows(), customerList);

		// 返回分页对象
		return page;
	}

	/**
	 * 2.根据id查询客户
	 */
	@Override
	public Customer queryCustomerById(Long custId) {
		return customerMapper.queryCustomerById(custId);
	}

	@Override
	public void updateCustomerById(Customer customer) {
		customerMapper.updateCustomerById(customer);
	}

	@Override
	public void deleteCustomerById(Long CustId) {
		customerMapper.deleteCustomerById(CustId);
	}

}
