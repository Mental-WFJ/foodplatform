package com.frontEnd.serviceimpl;

import com.frontEnd.dao.FavoritiesDao;
import com.frontEnd.entity.Favorities;
import com.frontEnd.service.FavoritiesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class FavoritiesServiceImpl implements FavoritiesService {

    @Resource
    private FavoritiesDao favoritiesDao;

    @Override
    public boolean insert(Favorities favorities){
        return favoritiesDao.insert(favorities) > 0 ? true : false;
    }

    @Override
    public boolean delete(Favorities favorities){
        return favoritiesDao.delete(favorities) > 0 ? true : false;
    }

    @Override
    public List<Favorities> getListByMemberID(Long id){
        return favoritiesDao.getListByMemberID(id);
    }

    @Override
    public List<Favorities> getListByTwo(Long id, String city, String key){
        return  favoritiesDao.getListByTwo(id, city, key);
    }

    @Override
    public List<Favorities> getListByThree(Long id, String city, String category, String key){
        return favoritiesDao.getListByThree(id, city, category, key);
    }
}
