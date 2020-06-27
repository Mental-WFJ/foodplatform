package com.rearEnd.dao;

import com.rearEnd.entity.City;

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
