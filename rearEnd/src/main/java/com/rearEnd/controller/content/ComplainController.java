package com.rearEnd.controller.content;

import com.rearEnd.constant.PageCodeEnum;
import com.rearEnd.entity.BusComplain;
import com.rearEnd.entity.Business;
import com.rearEnd.entity.ComComplain;
import com.rearEnd.entity.Comment;
import com.rearEnd.service.BusComplainService;
import com.rearEnd.service.BusinessService;
import com.rearEnd.service.ComComplainService;
import com.rearEnd.service.CommentService;
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
@RequestMapping("/complain")
public class ComplainController {

    @Resource
    private ComComplainService comComplainService;
    @Resource
    private BusComplainService busComplainService;
    @Resource
    private CommentService commentService;
    @Resource
    private BusinessService businessService;

    @RequestMapping("/comment")
    public String initComment(Model model, HttpServletRequest request) {
        ComComplain comComplain = new ComComplain();
        model.addAttribute("list", comComplainService.getComComplainByPage(comComplain));
        model.addAttribute("searchParam", comComplain);
        return "/content/comComplainList";
    }

    @RequestMapping("/search/comment")
    public String searchComment(Model model, ComComplain comComplain) {
        model.addAttribute("list", comComplainService.getComComplainByPage(comComplain));
        model.addAttribute("searchParam", comComplain);
        return "/content/comComplainList";
    }

    /**
     * 修改页初始化
     */
    @RequestMapping(value = "/comment/{id}")
    public String comModifyInit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("modifyObj", comComplainService.getComComplainByID(id));
        return "/content/comComplainModify";
    }

    /**
     * 修改
     */
    @RequestMapping("/comModify/{id}/{flag}")
    public String comModify(Model model, @PathVariable("id") Long id, @PathVariable("flag") Integer flag) {
        if(flag == 1){
            ComComplain c = comComplainService.getComComplainByID(id);
            System.out.println("commentId: " + c.getCommentId());
            commentService.update(c.getCommentId());
            comComplainService.updateFlag(id, 1);
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.COMPLAIN_SUCCESS);
        }
        else if(flag == 2){
            comComplainService.updateFlag(id, 2);
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.COMPLAIN_FAIL);
        }
        return "redirect:/complain/comment";
    }


    @RequestMapping("/business")
    public String initBusiness(Model model, HttpServletRequest request) {
        BusComplain busComplain = new BusComplain();
        model.addAttribute("list", busComplainService.getBusComplainByPage(busComplain));
        model.addAttribute("searchParam", busComplain);
        return "/content/busComplainList";
    }

    @RequestMapping("/search/business")
    public String searchBusiness(Model model, BusComplain busComplain) {
        model.addAttribute("list", busComplainService.getBusComplainByPage(busComplain));
        model.addAttribute("searchParam", busComplain);
        return "/content/busComplainList";
    }

    /**
     * 修改页初始化
     */
    @RequestMapping(value = "/business/{id}")
    public String busModifyInit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("modifyObj", busComplainService.getBusComplainByID(id));
        return "/content/busComplainModify";
    }

    /**
     * 修改
     */
    @RequestMapping("/busModify/{id}/{flag}")
    public String busModify(Model model, @PathVariable("id") Long id, @PathVariable("flag") Integer flag) {
        if(flag == 1){
            BusComplain busComplain = busComplainService.getBusComplainByID(id);
            Business b = new Business();
            b.setId(busComplain.getBusinessId());
            b.setUseable(0);
            System.out.println(busComplain.getBusinessId());
            businessService.modify(b);
            busComplainService.updateFlag(id, 1);
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.COMPLAIN_SUCCESS);
        }
        else if(flag == 2){
            busComplainService.updateFlag(id, 2);
            model.addAttribute("modifyObj", busComplainService.getBusComplainByID(id));
        }
        return "redirect:/complain/business";
    }
}
