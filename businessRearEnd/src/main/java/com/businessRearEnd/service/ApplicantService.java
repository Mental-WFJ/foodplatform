package com.businessRearEnd.service;

import com.businessRearEnd.entity.Applicant;

/**
 * @Author: Mental
 * @Date:
 */
public interface ApplicantService {

    public Applicant selectAppByPhone(String phone);

    public Applicant selectAppById(Long id);

    public boolean updatePwd(Long id, String pwd);

    public boolean updateInfo(Applicant applicant);
}
