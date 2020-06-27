package com.frontEnd.serviceimpl;

import com.frontEnd.dao.CategoryDao;
import com.frontEnd.entity.Category;
import com.frontEnd.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;

    public List<Category> getCategoryList(){
        return categoryDao.getCategoryList();
    }
}
