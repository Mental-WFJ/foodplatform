package com.businessRearEnd.controller;

import com.businessRearEnd.entity.Applicant;
import com.businessRearEnd.entity.Business;
import com.businessRearEnd.entity.Comment;
import com.businessRearEnd.entity.Orders;
import com.businessRearEnd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private HttpSession session;

    /**
     * 退出系统
     */
    @RequestMapping("/logout")
    public ModelAndView signOut() {
        return new ModelAndView("/login");
    }
}
