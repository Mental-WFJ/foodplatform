package com.rearEnd.serviceimpl;

import com.rearEnd.dao.BusComplainDao;
import com.rearEnd.entity.BusComplain;
import com.rearEnd.service.BusComplainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class BusComplainServiceImpl implements BusComplainService {

    @Resource
    private BusComplainDao busComplainDao;

    public List<BusComplain> getBusComplainByPage(BusComplain busComplain){
        return busComplainDao.getBusComplainByPage(busComplain);
    }

    public BusComplain getBusComplainByID(Long id){
        return busComplainDao.getBusComplainByID(id);
    }

    public boolean updateFlag(Long id, Integer flag){
        return busComplainDao.updateFlag(id, flag) > 0 ? true : false;
    }
}
