package com.frontEnd.dao;

import com.frontEnd.entity.Category;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface CategoryDao {

    /**
     * @return 种类列表
     */
    public List<Category> getCategoryList();
}
