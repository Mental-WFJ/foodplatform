package com.rearEnd.dao;

import com.rearEnd.entity.User;

import java.util.List;

public interface UserDao {

    /**
     * 根据查询条件查询管理员列表
     * @param user 查询条件
     * @return 管理员列表
     */
    List<User> select(User user);
    
    /**
     * 新增
     * @param user
     * @return 影响行数：如果管理员名已存在，影响行数为0，新增成功，影响行数为1
     */
    int insert(User user);
    
    /**
     * 根据主键获取管理员实体
     * @param id 主键
     * @return 管理员实体
     */
    User selectById(Long id);
    
    /**
     * 修改
     * @param user
     * @return 影响行数：如管理员名将修改成与其他管理员的管理员名相同，影响行数为0，修改成功，影响行数为1
     */
    int update(User user);
    
    /**
     * 根据主键删除
     * @param id 主键
     * @return 影响行数
     */
    int delete(Long id);
}