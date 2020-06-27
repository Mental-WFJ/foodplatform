package com.frontEnd.controller;

import com.frontEnd.entity.Ad;
import com.frontEnd.entity.Business;
import com.frontEnd.entity.City;
import com.frontEnd.entity.Dic;
import com.frontEnd.service.*;
import com.frontEnd.constant.DicTypeConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/webAPI")
public class IndexController {

    @Resource
    private AdService adService;
    @Resource
    private BusinessService businessService;
    @Resource
    private CityService cityService;
    @Resource
    private CategoryService categoryService;
    @Autowired
    private HttpSession session;

    @RequestMapping("/init")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/home");
        List<Ad> adList = adService.getAdList();
        mv.addObject("adList", adList);
        for (Ad ad : adList)
            System.out.println(ad.getTitle() + " " + ad.getLink() + " " + ad.getImgFileName() + " " + ad.getWeight());
        City c = new City();
        for(City city1 : cityService.getCityList()){
            if(city1.getId() == 1){
                c = city1;
                break;
            }
        }
        System.out.println(c.getId() + "、" + c.getCity());
        List<Business> businessList = businessService.getHotBusiness(c.getCity());
        List<Business> bList = new ArrayList<Business>();
        if(businessList.size() <= 6){
            for(int i=0; i<businessList.size(); i++){
                bList.add(businessList.get(i));
                System.out.println(bList.get(i).getTitle() + bList.get(i).getImgFileName());
            }
        }
        else{
            for(int i=0; i<6; i++){
                bList.add(businessList.get(i));
                System.out.println(bList.get(i).getTitle() + bList.get(i).getImgFileName());
            }
        }
        session.setAttribute("selectedCity", c);
        mv.addObject("businessList", bList);
        mv.addObject("cityList", cityService.getCityList());
        mv.addObject("categoryList", categoryService.getCategoryList());
        return mv;
    }

    @RequestMapping("/{cityId}")
    public ModelAndView indexCity(@PathVariable(value = "cityId") String cityId){
        int cId = Integer.parseInt(cityId);
        System.out.println("cityID: " + cId);
        ModelAndView mv = new ModelAndView("/home");
        List<Ad> adList = adService.getAdList();
        mv.addObject("adList", adList);
        for (Ad ad : adList)
            System.out.println(ad.getTitle() + " " + ad.getLink() + " " + ad.getImgFileName() + " " + ad.getWeight());
        City c = new City();
        for(City city1 : cityService.getCityList()){
            if(city1.getId() == cId){
                c = city1;
                break;
            }
        }
        List<Business> businessList = businessService.getHotBusiness(c.getCity());
        List<Business> bList = new ArrayList<Business>();
        if(businessList.size() <= 6){
            for(int i=0; i<businessList.size(); i++){
                bList.add(businessList.get(i));
                System.out.println(bList.get(i).getTitle() + bList.get(i).getImgFileName());
            }
        }
        else{
            for(int i=0; i<6; i++){
                bList.add(businessList.get(i));
                System.out.println(bList.get(i).getTitle() + bList.get(i).getImgFileName());
            }
        }
        session.setAttribute("selectedCity", c);
        mv.addObject("businessList", bList);
        mv.addObject("cityList", cityService.getCityList());
        mv.addObject("categoryList", categoryService.getCategoryList());
        return mv;
    }

    @RequestMapping("/clear")
    public String clearUser(){
        session.removeAttribute("member");
        return "redirect:/webAPI/init";
    }

    @RequestMapping("/apply")
    public ModelAndView toApply(){
        ModelAndView mv = new ModelAndView("/applyForBusiness");
        mv.addObject("categoryList", categoryService.getCategoryList());
        mv.addObject("cityList", cityService.getCityList());
        return mv;
    }

    @RequestMapping("/navigation/{businessId}")
    public ModelAndView navigetion(@PathVariable("businessId")Long businessId){
        ModelAndView mv = new ModelAndView("/walkNavigation");
        Business b = businessService.getById(businessId);
        mv.addObject("business", b);
        return mv;
    }
}
