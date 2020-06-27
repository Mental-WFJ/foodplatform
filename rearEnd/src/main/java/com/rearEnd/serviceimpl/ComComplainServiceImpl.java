package com.rearEnd.serviceimpl;

import com.rearEnd.dao.ComComplainDao;
import com.rearEnd.entity.ComComplain;
import com.rearEnd.service.ComComplainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class ComComplainServiceImpl implements ComComplainService {

    @Resource
    private ComComplainDao comComplainDao;

    public List<ComComplain> getComComplainByPage(ComComplain comComplain){
        return comComplainDao.getComComplainByPage(comComplain);
    }

    public ComComplain getComComplainByID(Long id){
        return comComplainDao.getComComplainByID(id);
    }

    public boolean updateFlag(Long id, Integer flag){
        return comComplainDao.updateFlag(id, flag) > 0 ? true : false;
    }
}
