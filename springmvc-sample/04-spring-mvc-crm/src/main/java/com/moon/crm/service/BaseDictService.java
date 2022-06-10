package com.moon.crm.service;

import java.util.List;

import com.moon.crm.po.BaseDict;

/**
 * 数据字典业务逻辑层接口
 * @author MoonZero
 */
public interface BaseDictService {

	/**
	 * 根据字典编号查询数据
	 */
	List<BaseDict> queryBaseDictByTypeCode(String typeCode);
}
