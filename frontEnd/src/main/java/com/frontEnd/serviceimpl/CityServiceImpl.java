package com.frontEnd.serviceimpl;

import com.frontEnd.dao.CityDao;
import com.frontEnd.entity.City;
import com.frontEnd.service.CityService;
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
