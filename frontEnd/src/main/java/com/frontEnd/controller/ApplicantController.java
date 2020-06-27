package com.frontEnd.controller;

import com.frontEnd.dto.ApplicantDto;
import com.frontEnd.entity.Applicant;
import com.frontEnd.entity.Category;
import com.frontEnd.entity.City;
import com.frontEnd.service.ApplicantService;
import com.frontEnd.service.CategoryService;
import com.frontEnd.service.CityService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Controller
@RequestMapping("/apply")
public class ApplicantController {

    @Resource
    private ApplicantService applicantService;
    @Autowired
    private HttpSession session;
    @Resource
    private CategoryService categoryService;
    @Resource
    private CityService cityService;

    @RequestMapping("/toSubmit")
    public ModelAndView toSubmit(HttpServletRequest request, ApplicantDto applicantDto){

        List<City> cityList = cityService.getCityList();
        List<Category> categoryList = categoryService.getCategoryList();
        for(City city : cityList){
            if(city.getId().equals(applicantDto.getBusinessCity())){
                applicantDto.setBusinessCity(city.getCity());
                break;
            }
        }
        for(Category category : categoryList){
            if(category.getId().equals(applicantDto.getBusinessCategory())){
                applicantDto.setBusinessCategory(category.getType());
                break;
            }
        }
        ModelAndView mv = new ModelAndView("/applyForBusiness");
        mv.addObject("cityList", cityList);
        mv.addObject("categoryList", categoryList);
        if(applicantDto.getApplicantPhone() != null && applicantDto.getApplicantPhone() != null && applicantDto.getBusinessAddress() != null && applicantDto.getBusinessName() != null && applicantDto.getImgFile() != null
        && !applicantDto.getApplicantName().equals("") && !applicantDto.getApplicantPhone().equals("") && !applicantDto.getBusinessName().equals("") && !applicantDto.getBusinessAddress().equals("") && !applicantDto.getImgFile().equals(""))
            applicantService.insertApp(applicantDto);
        return mv;
    }

    @RequestMapping("/checkPhone")
    @ResponseBody
    public boolean checkPhone(HttpServletRequest request){
        List<Applicant> applicants = applicantService.getApplicantList();
        String phone = request.getParameter("phone");
        for(Applicant a : applicants){
            if(a.getApplicantPhone().equals(phone)){
                System.out.println(phone);
                return true;
            }
        }
        return false;
    }
}
