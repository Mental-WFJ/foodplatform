package com.frontEnd.service;

import com.frontEnd.entity.Favorities;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface FavoritiesService {

    /**
     * 添加收藏
     *
     * @param favorities
     * @return 是否收藏成功：true-成功;fale-失败
     */
    public boolean insert(Favorities favorities);

    /**
     * 取消收藏
     *
     * @param favorities
     * @return true:删除成功;false:删除失败
     */
    public boolean delete(Favorities favorities);

    /**
     * 根据用户id查找用户收藏列表
     * @param id 用户id
     * @return 收藏列表
     */
    public List<Favorities> getListByMemberID(Long id);

    public List<Favorities> getListByTwo(Long id, String city, String key);

    public List<Favorities> getListByThree(Long id, String city, String category, String key);

}
