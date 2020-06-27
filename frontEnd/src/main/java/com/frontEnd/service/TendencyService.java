package com.frontEnd.service;

import com.frontEnd.entity.Tendency;

/**
 * @Author: Mental
 * @Date:
 */
public interface TendencyService {

    public boolean updateTendency(Tendency tendency);

    public Tendency getTendencyByID(Long id);
}
