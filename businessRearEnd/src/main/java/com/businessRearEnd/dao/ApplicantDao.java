package com.businessRearEnd.dao;

import com.businessRearEnd.entity.Applicant;

/**
 * @Author: Mental
 * @Date:
 */
public interface ApplicantDao {

    public Applicant selectAppByPhone(String phone);

    public Applicant selectAppById(Long id);

    public int updatePwd(Long id, String pwd);

    public int updateInfo(Applicant applicant);
}
