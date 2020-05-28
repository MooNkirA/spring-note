package com.moon.crm.mapper;

import java.util.List;

import com.moon.crm.po.BaseDict;

/**
 * 数据字典mapper接口
 * @author MoonZero
 */
public interface BaseDictMapper {

	/**
	 * 根据数据字典类型编码查询
	 */
	List<BaseDict> queryBaseDictByTypeCode(String typeCode);
}
