package com.businessRearEnd.controller;

import com.businessRearEnd.constant.PageCodeEnum;
import com.businessRearEnd.dto.BusinessDto;
import com.businessRearEnd.entity.Applicant;
import com.businessRearEnd.entity.Business;
import com.businessRearEnd.entity.Category;
import com.businessRearEnd.entity.City;
import com.businessRearEnd.service.ApplicantService;
import com.businessRearEnd.service.BusinessService;
import com.businessRearEnd.service.CategoryService;
import com.businessRearEnd.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Controller
@RequestMapping("/business")
public class BusinessController {

    @Resource
    private BusinessService businessService;
    @Resource
    private ApplicantService applicantService;
    @Autowired
    private HttpSession session;
    @Resource
    private CityService cityService;
    @Resource
    private CategoryService categoryService;


    @RequestMapping("/showInfo")
    public ModelAndView showAppInfo(){
        ModelAndView mv = new ModelAndView("/businessInfo");
        mv.addObject("categoryList", categoryService.getCategoryList());
        mv.addObject("cityList", cityService.getCityList());
        return mv;
    }

    @RequestMapping("/updateInfo/{applicantId}/{businessId}")
    public ModelAndView updateInfo(BusinessDto businessDto, @PathVariable("applicantId")Long applicantId, @PathVariable("businessId")Long businessId){
        ModelAndView mv = new ModelAndView("/businessInfo");
        List<City> cityList = cityService.getCityList();
        List<Category> categoryList = categoryService.getCategoryList();
        mv.addObject("cityList", cityList);
        mv.addObject("categoryList",categoryList);
        Applicant applicant = new Applicant();
        applicant.setId(applicantId);
        applicant.setBusinessName(businessDto.getTitle());
        applicant.setBusinessCity(businessDto.getCity());
        applicant.setBusinessCategory(businessDto.getCategory());
        for(City city : cityList){
            if(city.getId() == Integer.parseInt(businessDto.getCity())){
                businessDto.setCity(city.getCity());
                System.out.println("*******");
                break;
            }
        }
        for(Category category : categoryList){
            if(category.getId() == Integer.parseInt(businessDto.getCategory())){
                businessDto.setCategory(category.getType());
                System.out.println("--------");
                break;
            }
        }
        businessDto.setId(businessId);
        System.out.println(applicant.toString());
        System.out.println(businessDto.toString());
         if(businessService.updateInfo(businessDto) && applicantService.updateInfo(applicant))
             mv.addObject("message", PageCodeEnum.MODIFY_SUCCESS);
         else
             mv.addObject("message", PageCodeEnum.MODIFY_FAIL);
        Business b = businessService.seletByAppId(applicantId);
        session.setAttribute("business", b);
        System.out.println(b.toString());
         return mv;
    }

}
