package com.businessRearEnd.dao;

import com.businessRearEnd.entity.Orders;

import java.util.List;

public interface OrdersDao {

	/**
	 * 根据条件查询订单列表
	 * @param orders 查询条件
	 * @return 订单列表
	 */
	List<Orders> selectBybusinessId(Orders orders);
}