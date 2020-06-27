package com.frontEnd.controller;

import com.frontEnd.entity.BusComplain;
import com.frontEnd.entity.ComComplain;
import com.frontEnd.service.BusComplainService;
import com.frontEnd.service.ComComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Author: Mental
 * @Date:
 */
@Controller
@RequestMapping("/complain")
public class ComplainController {

    @Resource
    private ComComplainService comComplainService;
    @Resource
    private BusComplainService busComplainService;

    @RequestMapping("/addCom")
    @ResponseBody
    public boolean insertCom(HttpServletRequest request) throws ParseException {
        ComComplain comComplain = new ComComplain();
        Long commentId = Long.parseLong(request.getParameter("commentId"));
        Long memberId = Long.parseLong(request.getParameter("memberId"));
        String reason = request.getParameter("reason");
        comComplain.setCommentId(commentId);
        comComplain.setMemberId(memberId);
        comComplain.setReason(reason);
        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );
        comComplain.setCreateTime(time);
        System.out.println("commentId: " + commentId);
        System.out.println("reasaon: " + reason);
        return comComplainService.insertComComplain(comComplain);
    }

    @RequestMapping("/addBus")
    @ResponseBody
    public boolean insertBus(HttpServletRequest request) throws ParseException {
        BusComplain busComplain = new BusComplain();
        Long businessId = Long.parseLong(request.getParameter("businessId"));
        Long memberId = Long.parseLong(request.getParameter("memberId"));
        String reason = request.getParameter("reason");
        busComplain.setBusinessId(businessId);
        busComplain.setMemberId(memberId);
        busComplain.setReason(reason);
        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );
        busComplain.setCreateTime(time);
        System.out.println("businessId: " + businessId);
        System.out.println("reason: " + reason);
        return busComplainService.insertBusComplain(busComplain);
    }
}
