package com.rearEnd.controller.system;

import com.rearEnd.constant.PageCodeEnum;
import com.rearEnd.constant.SessionKeyConst;
import com.rearEnd.dto.GroupDto;
import com.rearEnd.dto.UserDto;
import com.rearEnd.service.GroupService;
import com.rearEnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 登录相关
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;
    @Resource
    private GroupService groupService;
    @Autowired
    private HttpSession session;

    /**
     * 登录页面
     */
    @RequestMapping
    public String index() {
        return "/system/login";
    }

    /**
     * session超时
     */
    @RequestMapping("/sessionTimeout")
    public String sessionTimeout(Model model) {
        model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.SESSION_TIMEOUT);
        return "/system/error";
    }

    /**
     * 没有权限访问
     */
    @RequestMapping("/noAuth")
    public String noAuth(Model model) {
        model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.NO_AUTH);
        session.invalidate();
        return "/system/error";
    }

    /**
     * 验证用户名/密码是否正确 验证通过跳转至后台管理首页，验证失败，返回至登录页。
     */
    @RequestMapping("/validate")
    public String validate(UserDto userDto, RedirectAttributes attr) {
        if (userService.validate(userDto)) {
            session.setAttribute(SessionKeyConst.USER_INFO, userDto);
            GroupDto groupDto = groupService.getByIdWithMenuAction(userDto.getGroupId());
            session.setAttribute(SessionKeyConst.MENU_INFO, groupDto.getMenuDtoList());
            session.setAttribute(SessionKeyConst.ACTION_INFO, groupDto.getActionDtoList());
            session.setAttribute("user", userDto);
            System.out.println(userDto.getChName());
            return "redirect:/index";
        }
        attr.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.LOGIN_FAIL);
        return "redirect:/login";
    }

}