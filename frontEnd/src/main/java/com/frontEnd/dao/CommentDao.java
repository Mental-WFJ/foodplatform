package com.frontEnd.dao;

import com.frontEnd.entity.Comment;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface CommentDao {

    /**
     * 根据订单id查询评论
     * @param id 订单号
     * @return 评论对象
     */
    Comment getByOrderID(Long id);

    int insertComment(Comment comment);
}
