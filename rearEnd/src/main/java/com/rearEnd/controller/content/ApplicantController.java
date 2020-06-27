package com.rearEnd.controller.content;

import com.rearEnd.constant.PageCodeEnum;
import com.rearEnd.entity.Applicant;
import com.rearEnd.entity.Business;
import com.rearEnd.entity.Category;
import com.rearEnd.entity.City;
import com.rearEnd.service.ApplicantService;
import com.rearEnd.service.BusinessService;
import com.rearEnd.service.CategoryService;
import com.rearEnd.service.CityService;
import com.rearEnd.util.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    private CityService cityService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private BusinessService businessService;


    @RequestMapping()
    public String initApplicant(Model model, HttpServletRequest request) {
        Applicant applicant = new Applicant();
        model.addAttribute("list", applicantService.getApplicantByPage(applicant));
        model.addAttribute("searchParam", applicant);
        return "/content/applicantList";
    }

    @RequestMapping("/search")
    public String searchApplicant(Model model, Applicant applicant) {
        model.addAttribute("list", applicantService.getApplicantByPage(applicant));
        model.addAttribute("searchParam", applicant);
        return "/content/applicantList";
    }

    @RequestMapping("/modifyInit/{id}")
    public String appModifyInit(Model model, @PathVariable("id") Long id) {
        Applicant applicant = applicantService.getApplicantByID(id);
        for (City c : cityService.getCityList()){
            if(c.getId() == Integer.parseInt(applicant.getBusinessCity())){
                applicant.setBusinessCity(c.getCity());
                break;
            }
        }
        for(Category c : categoryService.getCategoryList()){
            if(c.getId() == Integer.parseInt(applicant.getBusinessCategory())){
                applicant.setBusinessCategory(c.getType());
                break;
            }
        }
        model.addAttribute("modifyObj", applicant);
        return "/content/applicantModify";
    }

    @RequestMapping("/appModify/{id}/{flag}")
    public String comModify(Model model, @PathVariable("id") Long id, @PathVariable("flag") Integer flag) {
        if(flag == 1){
            Applicant a = applicantService.getApplicantByID(id);
            for (City c : cityService.getCityList()){
                if(c.getId() == Integer.parseInt(a.getBusinessCity())){
                    a.setBusinessCity(c.getCity());
                    break;
                }
            }
            for(Category c : categoryService.getCategoryList()){
                if(c.getId() == Integer.parseInt(a.getBusinessCategory())){
                    a.setBusinessCategory(c.getType());
                    break;
                }
            }
            Applicant applicant = new Applicant();
            applicant.setFlag(1);
            applicant.setLoginPassword(MD5Util.getMD5("123456"));
            applicant.setId(id);
            applicantService.updateApplicant(applicant);
            Business business = new Business();
            business.setTitle(a.getBusinessName());
            business.setAddress(a.getBusinessAddress());
            business.setCategory(a.getBusinessCategory());
            business.setCity(a.getBusinessCity());
            business.setAppId(a.getId());
            businessService.add(business);
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.COMPLAIN_SUCCESS);
        }
        else if(flag == 2){
            Applicant applicant = new Applicant();
            applicant.setFlag(2);
            applicant.setId(id);
            applicantService.updateApplicant(applicant);
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.COMPLAIN_FAIL);
        }
        return "redirect:/applicant";
    }
}
