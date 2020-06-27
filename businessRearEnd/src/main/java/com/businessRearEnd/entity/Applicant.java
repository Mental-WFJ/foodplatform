package com.businessRearEnd.entity;

/**
 * @Author: Mental
 * @Date:
 */
public class Applicant {

    private Long id;
    private String applicantName;
    private String applicantPhone;
    private String businessName;
    private String businessCity;
    private String businessCategory;
    private String businessAddress;
    private String businessImage;
    private int flag;
    private String loginPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessImage() {
        return businessImage;
    }

    public void setBusinessImage(String businessImage) {
        this.businessImage = businessImage;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", applicantName='" + applicantName + '\'' +
                ", applicantPhone='" + applicantPhone + '\'' +
                ", businessName='" + businessName + '\'' +
                ", businessCity='" + businessCity + '\'' +
                ", businessCategory='" + businessCategory + '\'' +
                ", businessAddress='" + businessAddress + '\'' +
                ", businessImage='" + businessImage + '\'' +
                ", flag=" + flag +
                ", loginPassword='" + loginPassword + '\'' +
                '}';
    }
}
