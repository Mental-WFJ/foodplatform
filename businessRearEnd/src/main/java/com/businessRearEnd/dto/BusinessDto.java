package com.businessRearEnd.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Mental
 * @Date:
 */
public class BusinessDto {
    private Long id;
    private String title;
    private String subtitle;
    private String city;
    private String category;
    private String desc;
    private MultipartFile imgFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    @Override
    public String toString() {
        return "BusinessDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", city='" + city + '\'' +
                ", category='" + category + '\'' +
                ", desc='" + desc + '\'' +
                ", imgFile=" + imgFile +
                '}';
    }
}
