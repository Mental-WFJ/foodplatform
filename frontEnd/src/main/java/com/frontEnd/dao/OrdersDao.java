package com.frontEnd.dao;

import com.frontEnd.entity.Orders;

import java.util.List;

public interface OrdersDao {
	
	/**
	 * 新增
	 * @param orders 订单表对象
	 * @return 影响行数
	 */
	int insert(Orders orders);
	
	/**
     * 根据主键查询订单表对象
     * @param id 主键值
     * @return 订单表对象
     */
	Orders selectById(Long id);
	
	/**
	 * 修改
	 * @param id 订单号
	 * @return 影响行数
	 */
	int update(Long id);
	
	/**
	 * 根据条件查询订单列表
	 * @param memberID 查询条件
	 * @return 订单列表
	 */
	List<Orders> selectByMemberId(Long memberID);

	/**
	 * 根据条件查询订单列表
	 * @param businessID 查询条件
	 * @return 订单列表
	 */
	List<Orders> selectBybusinessId(Long businessID);
}