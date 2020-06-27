package com.frontEnd.serviceimpl;

import com.frontEnd.dao.ApplicantDao;
import com.frontEnd.dto.ApplicantDto;
import com.frontEnd.entity.Applicant;
import com.frontEnd.service.ApplicantService;
import com.frontEnd.util.QiniuCloudUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Resource
    private ApplicantDao applicantDao;

    @RequestMapping
    public boolean insertApp(ApplicantDto applicantDto) {
        Applicant applicant = new Applicant();
        applicant.setApplicantName(applicantDto.getApplicantName());
        applicant.setApplicantPhone(applicantDto.getApplicantPhone());
        applicant.setBusinessName(applicantDto.getBusinessName());
        applicant.setBusinessCategory(applicantDto.getBusinessCategory());
        applicant.setBusinessCity(applicantDto.getBusinessCity());
        applicant.setBusinessAddress(applicantDto.getBusinessAddress());
        if (applicantDto.getImgFile() != null && applicantDto.getImgFile().getSize() > 0) {
            try {
                byte[] bytes = applicantDto.getImgFile().getBytes();
                String imageName = UUID.randomUUID().toString();
                QiniuCloudUtil qiniuUtil = new QiniuCloudUtil();
                try {
                    //使用base64方式上传到七牛云
                    String url = qiniuUtil.put64image(bytes, imageName);
                    applicant.setBusinessImage(url);
                    System.out.println(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                return false;
            }
        }
        return applicantDao.insertApp(applicant) > 0 ? true : false;
    }

    public List<Applicant> getApplicantList(){
        return applicantDao.getApplicantList();
    }
}
