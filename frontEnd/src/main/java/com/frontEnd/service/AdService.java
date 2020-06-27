package com.frontEnd.service;

import com.frontEnd.dto.AdDto;
import com.frontEnd.entity.Ad;

import java.util.List;

public interface AdService {

    /**
     * @return 广告列表
     */
    List<Ad> getAdList();

}
