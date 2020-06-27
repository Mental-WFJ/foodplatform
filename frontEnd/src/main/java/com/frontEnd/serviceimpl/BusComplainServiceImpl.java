package com.frontEnd.serviceimpl;

import com.frontEnd.dao.BusComplainDao;
import com.frontEnd.entity.BusComplain;
import com.frontEnd.service.BusComplainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class BusComplainServiceImpl implements BusComplainService {

    @Resource
    private BusComplainDao busComplainDao;

    public boolean insertBusComplain(BusComplain busComplain){
        return busComplainDao.insertBusComplain(busComplain) == 1 ? true : false;
    }
}
