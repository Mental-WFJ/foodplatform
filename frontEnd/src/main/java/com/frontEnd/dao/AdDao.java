package com.frontEnd.dao;

import com.frontEnd.entity.Ad;

import java.util.List;

public interface AdDao {

    /**
     * @return 广告列表
     */
    List<Ad> getAdList();
}