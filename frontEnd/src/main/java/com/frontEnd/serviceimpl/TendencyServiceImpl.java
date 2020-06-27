package com.frontEnd.serviceimpl;

import com.frontEnd.dao.TendencyDao;
import com.frontEnd.entity.Tendency;
import com.frontEnd.service.TendencyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class TendencyServiceImpl implements TendencyService {

    @Resource
    private TendencyDao tendencyDao;

    @Override
    public boolean updateTendency(Tendency tendency){
        return tendencyDao.updateTendency(tendency) > 0 ? true : false;
    }

    @Override
    public Tendency getTendencyByID(Long id){
        return tendencyDao.getTendencyByID(id);
    }
}
