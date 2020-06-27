package com.businessRearEnd.dao;

import com.businessRearEnd.entity.City;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface CityDao {

    /**
     * @return 城市列表
     */
    public List<City> getCityList();
}
