package com.frontEnd.controller;

import com.frontEnd.entity.Business;
import com.frontEnd.entity.Category;
import com.frontEnd.entity.City;
import com.frontEnd.entity.Favorities;
import com.frontEnd.service.CategoryService;
import com.frontEnd.service.CityService;
import com.frontEnd.service.FavoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Controller
@RequestMapping("/favorite")
public class FavoritiesController {

    @Resource
    private FavoritiesService favoritiesService;
    @Resource
    private CityService cityService;
    @Resource
    private CategoryService categoryService;
    @Autowired
    private HttpSession session;

    @RequestMapping("/add")
    @ResponseBody
    public boolean insertFavorite(HttpServletRequest request){
        Favorities favorities = new Favorities();
        Long businessId = Long.parseLong(request.getParameter("businessId"));
        Long memberId = Long.parseLong(request.getParameter("memberId"));
        favorities.setBusinessId(businessId);
        favorities.setMemberId(memberId);
        return favoritiesService.insert(favorities);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public boolean deleteFavorite(HttpServletRequest request){
        Favorities favorities = new Favorities();
        Long businessId = Long.parseLong(request.getParameter("businessId"));
        Long memberId = Long.parseLong(request.getParameter("memberId"));
        favorities.setBusinessId(businessId);
        favorities.setMemberId(memberId);
        return favoritiesService.delete(favorities);
    }

    @RequestMapping("search/{memberId}/{cityId}/{catagoryId}/{searchKey}")
    public ModelAndView searchFavList(@PathVariable(value = "memberId") Long memberId,
                                      @PathVariable(value = "cityId") String cityId,
                                      @PathVariable(value = "catagoryId") String catagoryId,
                                      @PathVariable(value = "searchKey") String searchKey){
        ModelAndView mv = new ModelAndView("/memberFav");
        int cId = Integer.parseInt(cityId);
        City c = new City();
        Category cate = new Category();
        for (City city1 : cityService.getCityList()) {
            if (city1.getId() == cId) {
                c = city1;
                break;
            }
        }
        if (searchKey.equals("null"))
            searchKey = "";
        List<Favorities> fList = new ArrayList<Favorities>();
        if (catagoryId.equals("null")) {
            fList = favoritiesService.getListByTwo(memberId, c.getCity(), searchKey);
            cate = null;
        } else {
            int cateId = Integer.parseInt(catagoryId);
            System.out.println("categoryId: " + cateId);
            for (Category category : categoryService.getCategoryList()) {
                if (category.getId() == cateId) {
                    cate = category;
                    break;
                }
            }
            fList = favoritiesService.getListByThree(memberId, c.getCity(), cate.getType(), searchKey);
        }
        System.out.println("id: " + memberId + " cityId: " + cityId + " categoryId: " + catagoryId + " key: " + searchKey);
        session.setAttribute("selectedCity", c);
        mv.addObject("fList", fList);
        session.setAttribute("selectedCategory", cate);
        mv.addObject("cityList", cityService.getCityList());
        mv.addObject("categoryList", categoryService.getCategoryList());
        return mv;
    }
}
