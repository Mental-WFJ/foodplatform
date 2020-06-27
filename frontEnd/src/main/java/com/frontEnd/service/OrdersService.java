package com.frontEnd.service;

import com.frontEnd.entity.Orders;

import java.util.List;

public interface OrdersService {

    boolean insertOrder(Orders orders);

    boolean updateOrder(Long id);

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