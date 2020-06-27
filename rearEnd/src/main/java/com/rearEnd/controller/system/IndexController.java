package com.rearEnd.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /**
     * 登录成功后，后台管理首页
     */
    @RequestMapping("/index")
    public String init() {
        return "/system/index";
    }
}
