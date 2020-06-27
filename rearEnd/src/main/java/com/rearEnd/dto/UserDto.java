package com.rearEnd.dto;

import com.rearEnd.entity.User;

public class UserDto extends User {

    private Integer pId;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}