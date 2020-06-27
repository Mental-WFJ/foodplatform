package com.rearEnd.dao;

import com.rearEnd.entity.BusComplain;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface BusComplainDao {

    public List<BusComplain> getBusComplainByPage(BusComplain busComplain);

    public BusComplain getBusComplainByID(Long id);

    public int updateFlag(Long id, Integer flag);
}
