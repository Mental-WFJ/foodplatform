package com.rearEnd.dao;

import com.rearEnd.entity.ComComplain;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface ComComplainDao {

    public List<ComComplain> getComComplainByPage(ComComplain comComplain);

    public ComComplain getComComplainByID(Long id);

    public int updateFlag(Long id, Integer flag);
}
