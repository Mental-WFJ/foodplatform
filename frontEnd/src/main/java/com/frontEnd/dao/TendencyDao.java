package com.frontEnd.dao;

import com.frontEnd.entity.Tendency;

/**
 * @Author: Mental
 * @Date:
 */
public interface TendencyDao {

    public int updateTendency(Tendency tendency);

    public Tendency getTendencyByID(Long id);
}
