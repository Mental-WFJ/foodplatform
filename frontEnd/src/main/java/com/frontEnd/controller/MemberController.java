package com.frontEnd.controller;

import com.frontEnd.constant.PageCodeEnum;
import com.frontEnd.entity.Member;
import com.frontEnd.service.MemberService;
import com.frontEnd.util.JsonView;
import com.frontEnd.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author: Mental
 * @Date:
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;
    @Autowired
    private HttpSession session;

    @RequestMapping("/memberLogin")
    @ResponseBody
    public String memberLogin(HttpServletRequest request, String codeNum){
        String pwd = request.getParameter("passwd");
        String phone = request.getParameter("phone");
        System.out.println("pwd: " + pwd + " codeNum: " + codeNum + " phone: " + phone);
        Member member = memberService.getMemberByPhone(Long.parseLong(phone));
        if(member == null)
            return JsonView.render(1);       //用户不存在
        System.out.println("member: " + member.getPhone() + " "+ member.getPassword());
        if(codeNum == "" || codeNum == null){
            if(MD5Util.getMD5(pwd).equals(member.getPassword())){
                session.setAttribute("member", member);
                System.out.println("Member has logined successfully.");
                return JsonView.render(0);
            }
            else{
                return JsonView.render(2);    //密码错误
            }
        }
        else{
            if(pwd.equals(codeNum)){
                session.setAttribute("member", member);
                System.out.println("Member has logined successfully.");
                return JsonView.render(0);
            }
            else
                return JsonView.render(3);   //验证码错误
        }
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public String register(String phone, String code, String codeNum) {
        System.out.println("Phone : " + phone + "\ncode : " + code + "\ncodeNum : " + codeNum + "***********");
        Member member = new Member();
        if(memberService.exists(Long.parseLong(phone)))
            return JsonView.render(1);
        else{
            if(code.equals(codeNum)){
                member.setPhone(Long.parseLong(phone));
                member.setName(null);
                member.setPassword(MD5Util.getMD5("123456"));
                if(memberService.add(member))
                    return JsonView.render(0);
                return JsonView.render(3);
            }
            else
                return JsonView.render(2);
        }
    }

    @RequestMapping("/updateInfo")
    @ResponseBody
    public int updateInfo(HttpServletRequest request){
        Long id = Long.parseLong(request.getParameter("memberId"));
        Long phone = Long.parseLong(request.getParameter("memberPhone"));
        Member member = memberService.getMemberByPhone(phone);
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String pwd2 = request.getParameter("pwd2");
        System.out.println("id: " + id + " name: " + name + " pwd: " + pwd + " pwd2: " + pwd2);
        if(pwd != null && pwd2 != null && !pwd.equals("") && !pwd2.equals("")){
            if(pwd.equals(pwd2))
                member.setPassword(MD5Util.getMD5(pwd));
            else
                return 2;
        }
        if(name != null && name != "")
            member.setName(name);
        if(memberService.update(member)){
            session.setAttribute("member", member);
            return 1;
        }
        return 0;
    }
}
