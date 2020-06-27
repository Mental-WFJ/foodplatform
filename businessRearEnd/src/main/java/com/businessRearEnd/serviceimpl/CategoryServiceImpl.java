package com.businessRearEnd.serviceimpl;

import com.businessRearEnd.dao.CategoryDao;
import com.businessRearEnd.entity.Category;
import com.businessRearEnd.service.CategoryService;
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
