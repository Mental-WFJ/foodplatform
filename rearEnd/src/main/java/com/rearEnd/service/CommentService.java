package com.rearEnd.service;

import com.rearEnd.dto.CommentForSubmitDto;
import com.rearEnd.dto.CommentListDto;
import com.rearEnd.entity.Page;

public interface CommentService {

    /**
     * 保存评论
     *
     * @param commentForSubmitDto 提交的评论DTO对象
     * @return 是否保存成功：true-成功，false-失败
     */
    boolean add(CommentForSubmitDto commentForSubmitDto);

    /**
     * 按分页条件，根据商户ID获取商户下的评论列表dto
     *
     * @param businessId 商户ID
     * @param page       分页对象
     * @return 评论列表dto
     */
    CommentListDto getListByBusinessId(Long businessId, Page page);

    boolean update(Long commentId);
}