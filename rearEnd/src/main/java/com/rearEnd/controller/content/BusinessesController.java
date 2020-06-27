package com.rearEnd.controller.content;

import com.rearEnd.constant.PageCodeEnum;
import com.rearEnd.dto.BusinessDto;
import com.rearEnd.entity.Business;
import com.rearEnd.service.BusinessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/businesses")
public class BusinessesController {

    @Resource
    private BusinessService businessService;

    @RequestMapping
    public String init(Model model, HttpServletRequest request) {
        BusinessDto dto = new BusinessDto();
        model.addAttribute("list", businessService.searchByPage(dto));
        model.addAttribute("searchParam", dto);
        return "/content/businessList";
    }

    /**
     * 商户列表
     */
    @RequestMapping("/getList")
    public String search(Model model, Business business) {
        model.addAttribute("list", businessService.searchByPage(business));
        model.addAttribute("searchParam", business);
        return "/content/businessList";
    }

    /**
     * 商户修改
     */
    @RequestMapping(value = "/modify/{id}/{flag}")
    public String modify(@PathVariable("id") Long id, @PathVariable("flag")Integer flag, Model model) {
        Business business = new Business();
        business.setId(id);
        business.setUseable(flag);
        if (businessService.modify(business)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_FAIL);
        }
        return "redirect:/businesses";
    }
}