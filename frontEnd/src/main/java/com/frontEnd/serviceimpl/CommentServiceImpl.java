package com.frontEnd.serviceimpl;

import com.frontEnd.dao.CommentDao;
import com.frontEnd.entity.Comment;
import com.frontEnd.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    public Comment getByOrderID(Long id){
        return commentDao.getByOrderID(id);
    }

    public boolean insertComment(Comment comment){
        return commentDao.insertComment(comment) > 0 ? true : false;
    }
}
