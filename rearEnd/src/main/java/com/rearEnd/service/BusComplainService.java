package com.rearEnd.service;

import com.rearEnd.entity.BusComplain;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface BusComplainService {

    public List<BusComplain> getBusComplainByPage(BusComplain busComplain);

    public BusComplain getBusComplainByID(Long id);

    public boolean updateFlag(Long id, Integer flag);
}
