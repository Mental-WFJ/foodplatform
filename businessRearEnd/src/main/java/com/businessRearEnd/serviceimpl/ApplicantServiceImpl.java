package com.businessRearEnd.serviceimpl;

import com.businessRearEnd.dao.ApplicantDao;
import com.businessRearEnd.dto.ApplicantDto;
import com.businessRearEnd.entity.Applicant;
import com.businessRearEnd.service.ApplicantService;
import com.businessRearEnd.util.QiniuCloudUtil;
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

    @Override
    public Applicant selectAppByPhone(String phone){
        if(phone != null)
            return applicantDao.selectAppByPhone(phone);
        return null;
    }

    public Applicant selectAppById(Long id){
        return applicantDao.selectAppById(id);
    }

    @Override
    public boolean updatePwd(Long id, String pwd){
        return applicantDao.updatePwd(id, pwd) > 0 ? true : false;
    }

    public boolean updateInfo(Applicant applicant){
        return applicantDao.updateInfo(applicant) > 0 ? true : false;
    }
}
