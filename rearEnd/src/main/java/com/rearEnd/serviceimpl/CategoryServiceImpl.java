package com.rearEnd.serviceimpl;

import com.rearEnd.dao.CategoryDao;
import com.rearEnd.entity.Category;
import com.rearEnd.service.CategoryService;
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
