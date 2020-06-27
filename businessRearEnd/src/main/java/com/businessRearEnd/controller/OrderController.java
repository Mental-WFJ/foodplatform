package com.businessRearEnd.controller;

import com.businessRearEnd.entity.Comment;
import com.businessRearEnd.entity.Orders;
import com.businessRearEnd.service.CommentService;
import com.businessRearEnd.service.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrdersService ordersService;
    @Resource
    private CommentService commentService;

    @RequestMapping("/showOrder/{businessId}")
    public ModelAndView showOrder(@PathVariable("businessId") Long businessId) {
        ModelAndView mv = new ModelAndView("/orderInfo");
        Orders order = new Orders();
        order.setBusinessId(businessId);
        List<Orders> orders = ordersService.selectBybusinessId(order);
        List<Comment> comments = new ArrayList<Comment>();
        int i = 0;
        for (Orders o : orders) {
            Comment comment = new Comment();
            comment.setOrdersId(o.getId());
            Comment c = commentService.getByPage(comment);
            if(c == null)
                continue;
            c.setOrders(o);
            comments.add(c);
        }
        mv.addObject("commentList", comments);
        mv.addObject("searchParam", new Orders());
        return mv;
    }
}
