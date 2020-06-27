package com.businessRearEnd.controller;

import com.businessRearEnd.dto.echarts.Option;
import com.businessRearEnd.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author: Mental
 * @Date:
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    @Resource
    private ReportService reportService;

    @RequestMapping()
    public ModelAndView indexMoney() {
        return new ModelAndView("/reportInfo");
    }

    @RequestMapping(value = "/showReport/{businessId}", method = RequestMethod.GET)
    @ResponseBody
    public Option showReport(@PathVariable("businessId")Long businessId){
        Option option = reportService.countOrder(businessId);
        return option;
    }
}
