package com.frontEnd.dao;

import com.frontEnd.entity.Favorities;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface FavoritiesDao {

    /**
     * 新增
     * @param favorities 收藏表对象
     * @return 影响行数
     */
    public int insert(Favorities favorities);

    /**
     * 删除
     * @param favorities 收藏表对象
     * @return 影响行数
     */
    public int delete(Favorities favorities);

    /**
     * 根据用户id查找用户收藏列表
     * @param id 用户id
     * @return 收藏列表
     */
    public List<Favorities> getListByMemberID(Long id);

    public List<Favorities> getListByTwo(Long id, String city, String key);

    public List<Favorities> getListByThree(Long id, String city, String category, String key);

}
