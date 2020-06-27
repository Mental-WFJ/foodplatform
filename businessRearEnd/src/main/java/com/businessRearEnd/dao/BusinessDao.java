package com.businessRearEnd.dao;

import com.businessRearEnd.entity.Business;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BusinessDao {

    public Business seletByAppId(Long id);

    public int updateInfo(Business business);
}
