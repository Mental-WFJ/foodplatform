package com.rearEnd.controller.system;

import com.rearEnd.constant.PageCodeEnum;
import com.rearEnd.dto.ActionDto;
import com.rearEnd.dto.PageCodeDto;
import com.rearEnd.service.ActionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 动作相关
 */
@RestController
@RequestMapping("/actions")
public class ActionsController {

    @Resource
    private ActionService actionService;

    /**
     * 新增动作
     */
    @RequestMapping(method = RequestMethod.POST)
    public PageCodeDto add(ActionDto actionDto) {
        PageCodeDto result;
        if (actionService.add(actionDto)) {
            result = new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.ADD_FAIL);
        }
        return result;
    }

    /**
     * 删除动作
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public PageCodeDto remove(@PathVariable("id") Long id) {
        PageCodeDto result;
        if (actionService.remove(id)) {
            result = new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.REMOVE_FAIL);
        }
        return result;
    }

    /**
     * 修改动作
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public PageCodeDto modify(ActionDto actionDto) {
        PageCodeDto result;
        if (actionService.modify(actionDto)) {
            result = new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
        } else {
            result = new PageCodeDto(PageCodeEnum.MODIFY_FAIL);
        }
        return result;
    }

    /**
     * 根据主键获取动作dto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ActionDto getById(@PathVariable("id") Long id) {
        return actionService.getById(id);
    }
}