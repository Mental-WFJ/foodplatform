package com.rearEnd.controller.system;

import com.rearEnd.constant.PageCodeEnum;
import com.rearEnd.dto.PageCodeDto;
import com.rearEnd.dto.UserDto;
import com.rearEnd.entity.User;
import com.rearEnd.service.UserService;
import com.rearEnd.util.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户相关
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UserService userService;

    /**
     * 获取用户列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getList() {
        return userService.getList();
    }

    /**
     * 新增用户
     */
    @RequestMapping(method = RequestMethod.POST)
    public PageCodeDto add(UserDto userDto) {
        PageCodeDto result;
        if (userService.add(userDto)) {
            result = new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.USERNAME_EXISTS);
        }
        return result;
    }

    /**
     * 根据主键获取用户dto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDto getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    /**
     * 修改用户
     */
    @RequestMapping(value = "/pwd/{id}")
    public PageCodeDto modifyPwd(HttpServletRequest request, @PathVariable("id")Long id) {
        PageCodeDto result;
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newPasswordAgain = request.getParameter("newPasswordAgain");
        System.out.println("old: " + oldPassword + " new: " + newPassword + " newAgagin: " + newPasswordAgain + " " + id);
        if(oldPassword == null || oldPassword.equals("") || newPassword == null || newPassword.equals("") || newPasswordAgain == null || newPasswordAgain.equals("")){
            result = new PageCodeDto(PageCodeEnum.MODIFY_FAIL);
            return result;
        }
        User user = userService.getById(id);
        UserDto userDto = new UserDto();
        if(user.getPassword().equals(MD5Util.getMD5(oldPassword))){
            if(newPassword.equals(newPasswordAgain)){
                userDto.setPassword(newPassword);
            }
        }
        userDto.setId(id);
        if (userService.modify(userDto)) {
            result = new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.MODIFY_FAIL);
        }
        return result;
    }


    /**
     * 修改用户
     */
    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public PageCodeDto modify(UserDto userDto) {
        PageCodeDto result;
        if(userService.modify(userDto)) {
            result = new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.USERNAME_EXISTS);
        }
        return result;
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public PageCodeDto remove(@PathVariable("id") Long id) {
        PageCodeDto result;
        if (userService.remove(id)) {
            result = new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.REMOVE_FAIL);
        }
        return result;
    }
}