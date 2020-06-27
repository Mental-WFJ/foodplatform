package com.businessRearEnd.serviceimpl;

import com.businessRearEnd.dao.CommentDao;
import com.businessRearEnd.entity.Comment;
import com.businessRearEnd.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    public Comment getByPage(Comment comment){
        return commentDao.getByPage(comment);
    }
}
