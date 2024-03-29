package com.frontEnd.entity;

public class Member {

    private Long id;
    private Long phone;
    private String name;
    private String password;
    private Integer useable;

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUseable(){
        return useable;
    }

    public void setUseable(Integer useable){
        this.useable = useable;
    }
}
