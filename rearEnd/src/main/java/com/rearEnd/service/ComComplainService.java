package com.rearEnd.service;

import com.rearEnd.entity.ComComplain;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface ComComplainService {

    public List<ComComplain> getComComplainByPage(ComComplain comComplain);

    public ComComplain getComComplainByID(Long id);

    public boolean updateFlag(Long id, Integer flag);
}
