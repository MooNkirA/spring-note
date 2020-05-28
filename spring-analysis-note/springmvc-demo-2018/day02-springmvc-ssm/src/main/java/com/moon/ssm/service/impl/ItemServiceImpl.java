package com.moon.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moon.ssm.entity.Item;
import com.moon.ssm.mapper.ItemMapper;
import com.moon.ssm.service.ItemService;

/**
 * 商品业务层实现类
 * @author MoonZero
 */
// 使用注解交给spring容器管理
@Service("itemService")
public class ItemServiceImpl implements ItemService {

	// 注入商品mapper代理对象
	@Autowired
	private ItemMapper itemMapper;

	@Override
	public List<Item> queryAllItems() {
		// 调用mapper代理查询所有数据的方法
		// 如果是条件查询需要传递example对象，但如果查询全部，直接传递null即可
		List<Item> items = itemMapper.selectByExample(null);
		// 返回查询的结果
		return items;
	}

	@Override
	public Item queryItemById(Integer itemId) {
		// 调用mapper接口根据id查询数据方法
		Item item = itemMapper.selectByPrimaryKey(itemId);
		// 返回查询的结果
		return item;
	}

	@Override
	public void updateItem(Item item) {
		// 调用mapper接口保存数据方法
		itemMapper.updateByPrimaryKeySelective(item);
	}

}
