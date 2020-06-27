package com.businessRearEnd.serviceimpl;

import com.businessRearEnd.dao.CityDao;
import com.businessRearEnd.entity.City;
import com.businessRearEnd.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class CityServiceImpl implements CityService {

    @Resource
    private CityDao cityDao;

    public List<City> getCityList(){
        return cityDao.getCityList();
    }
}
