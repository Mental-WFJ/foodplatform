package com.rearEnd.controller.system;

import com.rearEnd.constant.DicTypeConst;
import com.rearEnd.service.DicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private DicService dicService;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("httpMethodList", dicService.getListByType(DicTypeConst.HTTP_METHOD));
        return "/system/auth";
    }
}