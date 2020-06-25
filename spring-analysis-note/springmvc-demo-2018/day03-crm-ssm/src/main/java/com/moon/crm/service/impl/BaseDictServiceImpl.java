package com.moon.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moon.crm.mapper.BaseDictMapper;
import com.moon.crm.po.BaseDict;
import com.moon.crm.service.BaseDictService;

/**
 * 数据字典业务逻辑层实现类
 * @author MoonZero
 */
// 使用注解让spring容器管理
@Service
public class BaseDictServiceImpl implements BaseDictService {

	// 注入数据字典接口
	@Autowired
	private BaseDictMapper baseDictMapper;

	@Override
	public List<BaseDict> queryBaseDictByTypeCode(String typeCode) {
		// 调用接口查询方法
		return baseDictMapper.queryBaseDictByTypeCode(typeCode);
	}

}
