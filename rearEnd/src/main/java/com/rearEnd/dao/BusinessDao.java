package com.rearEnd.dao;

import com.rearEnd.entity.Business;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BusinessDao {
	
	/**
	 * 新增
	 * @param business 商户表对象
	 * @return 影响行数
	 */
	int insert(Business business);
    
    /**
     *  根据主键查询商户
     * @param id 主键
     * @return 商户对象
     */
    Business selectById(Long id);
    
    /**
     *  根据查询条件分页查询商户列表
     * @param business 查询条件
     * @return 商户列表
     */
    List<Business> selectByPage(Business business);

    
    /**
     *  根据查询条件分页查询商户列表 : 
     *  标题、副标题、描述三个过滤条件为模糊查询
     *  并且这三个过滤条件之间为或者的关系，用 OR 连接
     *  这三个过滤条件与其他过滤条件依然是并且关系，用 AND 连接
     * @param business 查询条件
     * @return 商户列表
     */
    List<Business> selectLikeByPage(Business business);
    
    /**
     * 更新商户的【统计评论星星总数】、【统计评论总次数】，商户的【星级】用这两个字段数据计算得出
     * @param map
     * @return
     */
    int updateStar(Map<String, Date> map);

    /**
     * 更新商户的【销售数量】
     * @param map
     * @return
     */
    int updateNumber(Map<String, Date> map);

    /**
     * 更新商户的【人均】
     * @param map
     * @return
     */
    int updatePrice(Map<String, Date> map);

    /**
     * 根据主键删除
     * @param id 主键
     * @return 影响行数
     */
    int delete(Long id);

    /**
     * 根据主键修改
     * @param business 待修改的商户对象
     * @return 影响行数
     */
    int update(Business business);
}
