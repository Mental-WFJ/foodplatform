package com.businessRearEnd.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Mental
 * @Date:
 */
public class ApplicantDto {

        private String applicantName;
        private String applicantPhone;
        private String businessName;
        private String businessCity;
        private String businessCategory;
        private String businessAddress;
        private MultipartFile imgFile;

        public MultipartFile getImgFile() {
            return imgFile;
        }

        public void setImgFile(MultipartFile imgFile) {
            this.imgFile = imgFile;
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


        @Override
        public String toString() {
            return "ApplicantDto{" +
                    "applicantName='" + applicantName + '\'' +
                    ", applicantPhone='" + applicantPhone + '\'' +
                    ", businessName='" + businessName + '\'' +
                    ", businessCity='" + businessCity + '\'' +
                    ", businessCategory='" + businessCategory + '\'' +
                    ", businessAddress='" + businessAddress + '\'' +
                    ", imgFile=" + imgFile +
                    '}';
        }
}
