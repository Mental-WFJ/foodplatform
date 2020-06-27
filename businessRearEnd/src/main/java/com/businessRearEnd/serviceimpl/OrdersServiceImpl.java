package com.businessRearEnd.serviceimpl;

import com.businessRearEnd.dao.OrdersDao;
import com.businessRearEnd.entity.Orders;
import com.businessRearEnd.service.OrdersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrdersDao ordersDao;

    public List<Orders> selectBybusinessId(Orders orders){
        return ordersDao.selectBybusinessId(orders);
    }
}
