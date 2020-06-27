package com.rearEnd.service;

import com.rearEnd.entity.Applicant;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface ApplicantService {

    public List<Applicant> getApplicantByPage(Applicant applicant);

    public Applicant getApplicantByID(Long id);

    public boolean updateApplicant(Applicant applicant);
}
