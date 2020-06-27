package com.frontEnd.serviceimpl;

import com.frontEnd.dao.ComComplainDao;
import com.frontEnd.entity.ComComplain;
import com.frontEnd.service.ComComplainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class ComComplainServiceImpl implements ComComplainService {

    @Resource
    private ComComplainDao comComplainDao;

    @Override
    public boolean insertComComplain(ComComplain comComplain){
        return comComplainDao.insertComComplain(comComplain) == 1 ? true : false;
    }
}
