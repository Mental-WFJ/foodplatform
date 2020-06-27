package com.businessRearEnd.service;

import com.businessRearEnd.dto.BusinessDto;
import com.businessRearEnd.entity.Business;

/**
 * @Author: Mental
 * @Date:
 */
public interface BusinessService {

    public Business seletByAppId(Long id);

    public boolean updateInfo(BusinessDto businessDto);
}
