package com.businessRearEnd.dao;

import com.businessRearEnd.entity.Comment;

/**
 * @Author: Mental
 * @Date:
 */
public interface CommentDao {

    /**
     * 根据查询条件分页查询评论列表
     * @param comment 查询条件
     * @return 评论列表
     */
    public Comment getByPage(Comment comment);

}
