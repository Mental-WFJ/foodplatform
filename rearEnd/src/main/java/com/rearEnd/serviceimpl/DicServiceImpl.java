package com.rearEnd.serviceimpl;

import com.rearEnd.dao.DicDao;
import com.rearEnd.entity.Dic;
import com.rearEnd.service.DicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class DicServiceImpl implements DicService {

    @Resource
    private DicDao dicDao;

    @Override
    public List<Dic> getListByType(String type) {
        Dic dic = new Dic();
        dic.setType(type);
        return dicDao.select(dic);
    }
}
