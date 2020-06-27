package com.rearEnd.dao;

import com.rearEnd.entity.Applicant;

import java.util.List;

/**
 * @Author: Mental
 * @Date:
 */
public interface ApplicantDao {

    public List<Applicant> getApplicantByPage(Applicant applicant);

    public Applicant getApplicantByID(Long id);

    public int updateApplicant(Applicant applicant);
}
