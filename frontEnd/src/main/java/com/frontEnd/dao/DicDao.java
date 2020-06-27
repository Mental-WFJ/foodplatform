package com.frontEnd.dao;

import com.frontEnd.entity.Dic;

import java.util.List;

public interface DicDao {
    List<Dic> select(Dic dic);
}