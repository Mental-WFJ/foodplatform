package com.frontEnd.dao;

import com.frontEnd.entity.City;

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
