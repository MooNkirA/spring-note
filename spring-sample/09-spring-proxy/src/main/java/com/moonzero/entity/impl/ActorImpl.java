package com.moonzero.entity.impl;

import com.moonzero.entity.IActor;

/**
 * 接口实现类
 * 
 * @author MoonZero
 */
public class ActorImpl implements IActor {

	@Override
	public void basicAct(float money) {
		System.out.println("拿到 " + money + " 元，开始基本的表演!!");
	}

	@Override
	public void wonderfulAct(float money) {
		System.out.println("拿到 " + money + " 元，开始精彩的表演!!");
	}

}
