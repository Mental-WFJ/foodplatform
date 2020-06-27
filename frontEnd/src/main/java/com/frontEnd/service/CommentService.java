package com.frontEnd.service;

import com.frontEnd.entity.Comment;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface CommentService {
    public Comment getByOrderID(Long id);

    public boolean insertComment(Comment comment);
}
