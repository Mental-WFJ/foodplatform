package com.frontEnd.serviceimpl;

import com.frontEnd.dao.DicDao;
import com.frontEnd.entity.Dic;
import com.frontEnd.service.DicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
