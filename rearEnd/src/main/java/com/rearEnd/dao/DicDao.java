package com.rearEnd.dao;

import com.rearEnd.entity.Dic;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface DicDao {
    List<Dic> select(Dic dic);
}
