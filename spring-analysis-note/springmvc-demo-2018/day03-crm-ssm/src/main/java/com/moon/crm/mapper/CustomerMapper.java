package com.moon.crm.mapper;

import java.util.List;

import com.moon.crm.po.Customer;
import com.moon.crm.po.QueryVo;

/**
 * 客户模块mapper代理接口
 * @author MoonZero
 */
public interface CustomerMapper {

	/**
	 * 1.根据多条件查询的客户数据集合
	 */
	List<Customer> queryCustomerList(QueryVo queryVo);

	/**
	 * 2.根据多条件查询数据的总记录数
	 */
	Integer countCustomers(QueryVo queryVo);

	/**
	 * 3.根据客户id查询客户数据
	 */
	Customer queryCustomerById(Long custId);

	/**
	 * 4.根据客户id修改客户数据
	 */
	void updateCustomerById(Customer customer);
	
	/**
	 * 5.根据客户id删除客户数据
	 */
	void deleteCustomerById(Long CustId);
	

}
