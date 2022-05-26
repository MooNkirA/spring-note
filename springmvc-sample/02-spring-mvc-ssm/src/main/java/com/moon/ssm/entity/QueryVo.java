package com.moon.ssm.entity;

import java.io.Serializable;

/**
 * 包装类，用于封装复杂请求提交参数
 */
public class QueryVo implements Serializable {
	private static final long serialVersionUID = 8434485836034044717L;

	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
