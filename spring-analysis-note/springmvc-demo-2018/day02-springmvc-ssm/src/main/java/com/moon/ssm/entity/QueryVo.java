package com.moon.ssm.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 包装类，用于封装复杂请求提交参数
 */
public class QueryVo implements Serializable {
	private static final long serialVersionUID = 8434485836034044717L;

	private Item item;

	// 测试包装类数组类型绑定
	private Integer[] ids;

	// 测试list类数组类型
	private List<Item> itemList;

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
