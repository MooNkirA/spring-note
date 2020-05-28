package com.moon.ssm.service;

import java.util.List;

import com.moon.ssm.entity.Item;

/**
 * 商品业务逻辑层接口
 * @author MoonZero
 */
public interface ItemService {

	/**
	 * 查询所有商品列表
	 * @return List<Item>
	 */
	List<Item> queryAllItems();

	/**
	 * 根据商品id查询商品
	 * @param itemId
	 * @return Item
	 */
	Item queryItemById(Integer itemId);

	/**
	 * 修改商品数据，保存到数据库
	 */
	void updateItem(Item item);
}
