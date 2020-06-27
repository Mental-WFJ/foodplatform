package com.rearEnd.serviceimpl;

import com.rearEnd.constant.CommentStateConst;
import com.rearEnd.dao.CommentDao;
import com.rearEnd.dao.OrdersDao;
import com.rearEnd.dto.CommentDto;
import com.rearEnd.dto.CommentForSubmitDto;
import com.rearEnd.dto.CommentListDto;
import com.rearEnd.entity.Business;
import com.rearEnd.entity.Comment;
import com.rearEnd.entity.Orders;
import com.rearEnd.entity.Page;
import com.rearEnd.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;
    @Resource
    private OrdersDao ordersDao;

    @Override
    @Transactional
    public boolean add(CommentForSubmitDto commentForSubmitDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentForSubmitDto, comment);
        comment.setId(null);
        comment.setOrdersId(commentForSubmitDto.getId());
        comment.setCreateTime(new Date());
        // 保存评论
        commentDao.insert(comment);
        Orders orders = new Orders();
        orders.setId(commentForSubmitDto.getId());
        orders.setCommentState(CommentStateConst.HAS_COMMENT);
        // 更新订单评论状态
        ordersDao.update(orders);
        return true;
    }

    @Override
    public CommentListDto getListByBusinessId(Long businessId, Page page) {
        CommentListDto result = new CommentListDto();

        // 组织查询条件
        Comment comment = new Comment();
        Orders orders = new Orders();
        Business business = new Business();
        // 评论里包含了订单对象
        comment.setOrders(orders);
        // 订单对象里包含了商户对象
        orders.setBusiness(business);
        // 设置商户主键
        business.setId(businessId);
        // 前端app页码从0开始计算，这里需要+1
        page.setCurrentPage(page.getCurrentPage() + 1);
        // 设置分页条件
        comment.setPage(page);
        List<Comment> commentList = commentDao.selectByPage(comment);

        // 组织返回值
        List<CommentDto> data = new ArrayList<>();
        result.setData(data);
        for (Comment commentTemp : commentList) {
            CommentDto commentDto = new CommentDto();
            data.add(commentDto);
            BeanUtils.copyProperties(commentTemp, commentDto);
            // 隐藏手机号中间4位
            StringBuffer phoneBuffer = new StringBuffer(String.valueOf(commentTemp.getOrders().getMember().getPhone()));
            commentDto.setUsername(phoneBuffer.replace(3, 7, "****").toString());
        }
        result.setHasMore(page.getCurrentPage() < page.getTotalPage());
        return result;
    }


    public boolean update(Long commentId){
        return commentDao.update(commentId) > 0 ? true : false;
    }
}
