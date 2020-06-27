package com.businessRearEnd.controller;

import com.businessRearEnd.constant.PageCodeEnum;
import com.businessRearEnd.entity.Applicant;
import com.businessRearEnd.entity.Business;
import com.businessRearEnd.service.ApplicantService;
import com.businessRearEnd.service.BusinessService;
import com.businessRearEnd.service.CategoryService;
import com.businessRearEnd.service.CityService;
import com.businessRearEnd.util.MD5Util;
import com.qiniu.util.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: Mental
 * @Date:
 */
@Controller
@RequestMapping("/applicant")
public class ApplicantController {

    @Resource
    private ApplicantService applicantService;
    @Resource
    private BusinessService businessService;
    @Autowired
    private HttpSession session;

    @RequestMapping("/login")
    public ModelAndView loginApp(HttpServletRequest request, RedirectAttributes attr){
        ModelAndView mv;
        String phone = request.getParameter("phone");
        String pwd = MD5Util.getMD5(request.getParameter("password"));
        System.out.println("Phone: " + phone + " Password: " + pwd);
        Applicant applicant = applicantService.selectAppByPhone(phone);
        System.out.println(applicant.toString());
        if(applicant.getLoginPassword().equals(pwd) && applicant.getFlag() == 1){
             mv = new ModelAndView("/index");
            session.setAttribute("applicant", applicant);
            Business business = businessService.seletByAppId(applicant.getId());
            System.out.println(business.getId() + " " + business.getTitle());
            session.setAttribute("business", business);
            System.out.println("Login Successfully.");
        }
        else{
            mv = new ModelAndView("/login");
            attr.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.LOGIN_FAIL);
            System.out.println("Login Failed.");
        }
        return mv;
    }

    @RequestMapping("/changePwd/{applicantId}")
    public int changePwd(HttpServletRequest request, @PathVariable("applicantId") Long applicantId){
        String pwd = request.getParameter("oldPassword");
        String pwd2 = request.getParameter("newPassword");
        String pwd3 = request.getParameter("newPasswordAgain");
        if(pwd2 != null && pwd3 != null){
            if(pwd2.equals(pwd3)){
                if(applicantService.updatePwd(applicantId, MD5Util.getMD5(pwd2)))
                    return 1;
                else
                    return 0;
            }
            else
                return 2;
        }
        else
            return 0;
    }
}
