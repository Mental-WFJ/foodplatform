package com.frontEnd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.frontEnd.entity.Business;
import org.springframework.web.multipart.MultipartFile;

@JsonInclude(Include.NON_NULL)
public class BusinessDto extends Business {

    private String img;
    private MultipartFile imgFile;
    private String keyword;
    private Integer number;
    private Long star;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getStar() {
        return star;
    }

    public void setStar(Long star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return super.toString() + "BusinessDto{" +
                "img='" + img + '\'' +
                ", imgFile=" + imgFile +
                ", keyword='" + keyword + '\'' +
                ", number=" + number +
                ", star=" + star +
                '}';
    }
}