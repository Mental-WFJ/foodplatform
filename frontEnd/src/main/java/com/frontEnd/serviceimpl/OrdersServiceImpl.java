package com.frontEnd.serviceimpl;

import com.frontEnd.dao.OrdersDao;
import com.frontEnd.entity.Orders;
import com.frontEnd.service.OrdersService;
import org.aspectj.weaver.ast.Or;
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

    public boolean insertOrder(Orders orders){
        return ordersDao.insert(orders) > 0 ? true : false;
    }

    public boolean updateOrder(Long id){
        return ordersDao.update(id) > 0 ? true : false;
    }

    public List<Orders> selectByMemberId(Long memberID){
        return ordersDao.selectByMemberId(memberID);
    }

    public List<Orders> selectBybusinessId(Long businessID){
        return ordersDao.selectBybusinessId(businessID);
    }
}
