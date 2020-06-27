package com.rearEnd.serviceimpl;

import com.rearEnd.dao.CityDao;
import com.rearEnd.entity.City;
import com.rearEnd.service.CityService;
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
