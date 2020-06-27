package com.rearEnd.serviceimpl;

import com.rearEnd.dao.ApplicantDao;
import com.rearEnd.entity.Applicant;
import com.rearEnd.service.ApplicantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Resource
    private ApplicantDao applicantDao;

    public List<Applicant> getApplicantByPage(Applicant applicant){
        return applicantDao.getApplicantByPage(applicant);
    }

    public Applicant getApplicantByID(Long id){
        return applicantDao.getApplicantByID(id);
    }

    public boolean updateApplicant(Applicant applicant){
        return applicantDao.updateApplicant(applicant) > 0 ? true : false;
    }
}
