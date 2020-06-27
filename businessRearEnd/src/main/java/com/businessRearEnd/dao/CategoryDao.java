package com.businessRearEnd.dao;

import com.businessRearEnd.entity.Category;

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
