package com.moon.crm.service;

import com.moon.crm.po.Customer;
import com.moon.crm.po.QueryVo;
import com.moon.crm.utils.Page;

/**
 * 客户模块业务逻辑层接口
 * @author MoonZero
 */
public interface CustomerService {

	/**
	 * 1.多条件分页查询客户数据
	 * @param queryVo 多条件查询包装类
	 * @return 客户的分页对象
	 */
	Page<Customer> queryCustomerByPage(QueryVo queryVo);

	/**
	 * 2.根据id查询客户
	 */
	Customer queryCustomerById(Long custId);

	/**
	 * 3.根据id修改客户数据
	 */
	void updateCustomerById(Customer customer);
	
	/**
	 * 4.根据id删除客户数据
	 */
	void deleteCustomerById(Long CustId);
}
